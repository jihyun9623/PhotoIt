package com.ssafy.api.service;

import com.ssafy.api.request.UserReq;
import com.ssafy.api.response.MyPageGetRes;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.AuthorLocationRepository;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AuthorLocationRepository authorLocationRepository;
    @Autowired
    private MyStudioRepository myStudioRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final StringRedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenProvider;

    public boolean isValidToken(String token){
        if(token!=null && token.length()>0) {
            logger.debug("token 검증");
            return jwtTokenProvider.validateToken(token);
        }else {
            throw new RuntimeException("인증 토큰이 없습니다");
        }
    }
    @Override
    public List<String> locationList() {
        List<String> loc_list=new ArrayList<>();
        List<Location> list = locationRepository.findAll();
        for(Location l:list){
            loc_list.add(l.getName());
        }
        return loc_list;
    }

    @Override
    public boolean upgradePhotographer(String id) {
        User member = userRepository.findUserById(id).
                orElseThrow(()->new IllegalArgumentException("id 찾기 에러 발생"));

        // 1. 비작가회원일 때만 업그레이드 가능
        if(member.getPg()==false){
            member.upgradePhotographer();       // 일단 유저 업그레이드 후 저장
            userRepository.save(member);
            MyStudio myStudio=MyStudio.builder()    // 유저에 해당하는 myStudio 생성
                    .nickname(member.getNickname())
                    .user(member)
                    .build();
            myStudioRepository.save(myStudio);
            return true;
        }else{
            return false;
        }
    }

    @Override
    /* 회원가입 */
    public void signUp(UserReq info) {

        // 작가회원이면 mystudio + location 생성
        if(info.getPg()==true){
            // 1. 일단 유저는 만들어서 저장.
            User user=User.builder()
                    .id(info.getId())
                    .nickname(info.getNickname())
                    .passwd(passwordEncoder.encode(info.getPasswd()))   //decoding how?
                    //.roles(Collections.singletonList("ROLE_USER"))
                    .role("PG")
                    .pg(info.getPg())
                    .photo(info.getPhoto())
                    .build();
            userRepository.save(user);
            PhotoGrapherSetting(user, info);
        }else{
            // 유저만 만들어서 저장. Role이 다르다!
            User user=User.builder()
                    .id(info.getId())
                    .nickname(info.getNickname())
                    .passwd(passwordEncoder.encode(info.getPasswd()))   //decoding how?
                 //   .roles(Collections.singletonList("ROLE_USER"))
                    .role("USER")
                    .pg(info.getPg())
                    .photo(info.getPhoto())
                    .build();
            userRepository.save(user);
        }
    }

    /* myStudio+location 생성 */
    public void PhotoGrapherSetting(User user, UserReq info){
        MyStudio myStudio=MyStudio.builder()
                .nickname(info.getNickname())
                .profile(info.getProfile())
                .user(user)
                .build();
        myStudioRepository.save(myStudio);

        /** 지역부터 Location에 추가해준다.*/
        for(String s:info.getLocation()){
            // 이미 있는 지역이 아니라면 location 테이블에 추가.
            if(locationRepository.findLocationByName(s)==null){
                Location loc=Location.builder().name(s).build();
                locationRepository.save(loc);
            }
            Location loc=locationRepository.findLocationByName(s);
            AuthorLocation authLoc=AuthorLocation.builder()
                    .location(loc)
                    .myStudio(myStudio)
                    .build();
            authorLocationRepository.save(authLoc);
        }
    }

    @Override
    /* 로그인 */
    public String signin(UserReq loginInfo) {
        logger.debug("로그인 메서드 진입");
        User member=userRepository.findUserById(loginInfo.getId())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(loginInfo.getPasswd(), member.getPasswd())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        logger.debug("로그인 성공 : " + member.getId());
        return jwtTokenProvider.createToken(member.getId(), member.getRole());
       // return jwtTokenProvider.createToken(member.getId(), member.getRole());
    }

    @Override
    /* 로그아웃 */
    public BaseResponseBody signOut(String token) {
        ValueOperations<String, String>logoutValueOpations=redisTemplate.opsForValue();
        User member= (User) jwtTokenProvider.getAuthentication(token).getPrincipal();
        logger.debug("로그아웃 유저 아이디 : {}, 유저 이름 : '{}'", member.getId(), member.getNickname());
        return BaseResponseBody.of(200, "Success Logout");
    }


    @Override
    /**
     * 회원정보 불러오기
     * */
    public MyPageGetRes getProfile(String token) {
        String id = jwtTokenProvider.getUserInfo(token);
        logger.debug("토큰에서 뽑아낸 user id : "+id);
        User member=userRepository.findUserById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다."));
        MyPageGetRes res;
        if(member.getPg()==true){   // 작가면
            MyStudio memberStudio=myStudioRepository.findByUserIdx(member.getIdx());
            List<AuthorLocation>list=authorLocationRepository.findAuthorLocationByMyStudio(memberStudio);
            List<String>memberList=new ArrayList<>();
            for(AuthorLocation loc:list){
                Location l=loc.getLocation();
                memberList.add(l.getName());
            }
            res= MyPageGetRes.builder()
                    .id(member.getId())
                    .nickname(member.getNickname())
                    .pg(member.getPg())
                    .photo(member.getPhoto())
                    .introduce(memberStudio.getProfile())
                    .Location(memberList).build();
        }else{
            res= MyPageGetRes.builder()
                    .id(member.getId())
                    .nickname(member.getNickname())
                    .pg(member.getPg())
                    .photo(member.getPhoto())
                    .build();
        }
        return res;
    }

    @Override
    /**
     * 비밀번호 확인 메서드
     * */
    public Boolean isPasswordRight(String id, String passwd) {
        User member=userRepository.findUserById(id)
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(passwd, member.getPasswd())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return true;
    }

    @Override
    /**
     * 회원정보 수정 메서드
     * */
    public void updateProfile(UserReq updateInfo) {
        logger.debug("updateProfile 진입");
        // 수정할 정보 : passwd, nickname, pg, location, introduce

        User member=userRepository.findById(updateInfo.getId())
                .orElseThrow(()->new IllegalArgumentException("없는 아이디입니다."));

        updateInfo.setPasswd(passwordEncoder.encode(updateInfo.getPasswd()));
        // 작가 업데이트
        if(updateInfo.getPg()==true) {
            logger.debug("작가 -> 작가");
            member.updateUserProfile(updateInfo);   // 일단 유저 정보 업데이트

            logger.debug("뽑아온 유저 idx : " + member.getIdx());        // 해당 유저 id를 가진 mystudio 객체를 가져옴
            MyStudio memberStudio = myStudioRepository.findByUserIdx(member.getIdx());

             memberStudio.updateMyStudioProfile(updateInfo, member);     // mystudio 업데이트 - clear

            // location 업데이트
            // 1. 일단,,, 데이터 다 지웠다. 해당하는 지역. -> 이거 바꾸는 게 좋을듯.
            authorLocationRepository.deleteAuthorLocationByMyStudio(memberStudio);

            for (String name : updateInfo.getLocation()) {
                // 이미 있는 지역이 아니라면 location 테이블에 추가.
                if (locationRepository.findLocationByName(name) == null) {
                    Location loc = Location.builder().name(name).build();
                    locationRepository.save(loc);
                }
                Location loc = locationRepository.findLocationByName(name);
                AuthorLocation authLoc = AuthorLocation.builder()
                        .location(loc)
                        .myStudio(memberStudio)
                        .build();
                authorLocationRepository.save(authLoc);
            }
        }
        // 비작가 업데이트
        else{
            member.updateUserProfile(updateInfo);

            logger.debug("비작가 -> 비작가");
        }
        userRepository.save(member);
    }

    @Override
    /**
     * 유저 id를 가지고 회원 탈퇴
     * */
    public void withdrawalUser(String id) {
        User member=userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다."));
        if(member.getPg()==true){
            logger.debug("작가 제거");
            // 작가면 일단 MyStudio랑 location부터 지워야됨
            MyStudio myStudio=myStudioRepository.findByIdx(member.getIdx());
            authorLocationRepository.deleteAuthorLocationByMyStudio(myStudio);
            myStudioRepository.deleteMyStudioByUser(member);
        }
        userRepository.delete(member);
        userRepository.flush();
        logger.debug("제거 완료");
    }

    @Override
    public boolean nicknameDuplicateCheck(String nickname) {
        if(userRepository.findByNickname(nickname).isPresent()){
            return true;    // null이 아니라면 중복
        }
        return false;
    }

    @Override
    public boolean idDuplicateCheck(String id) {
        if(userRepository.findUserById(id).isPresent()){
            return true;    // null이 아니라면 중복
        }
        return false;
    }


    public void testToken(String token){
       //Map<String, Object> data = jwtTokenProvider.get(token);
        jwtTokenProvider.getAuthentication(token);
    }

    /**
     * 프로필 사진 수정
     * */
    @Override
    public void editProfilePhoto(String token, UserReq photo) {
        String id = jwtTokenProvider.getUserInfo(token);
        User member=userRepository.findUserById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다"));
        member.updateUserPhoto(photo);
       userRepository.save(member);
    }

}
