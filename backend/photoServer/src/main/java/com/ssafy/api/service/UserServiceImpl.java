package com.ssafy.api.service;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.AuthorLocationRepository;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.UserRepository;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

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


    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenProvider;

    @Override
    public void signUp(UserRegisterPostReq info) {
        /** User 테이블에 저장
            // id, passwd, nickname, pg, photo
        // mystudio 테이블에 저장
            // nickname, profile
        // author_location 테이블에 저장
            // location 저장
        // location 테이블에 저장
            // location
        */

        // 작가회원이면 mystudio + location 생성
        if(info.getPg()==true){
            // 1. 일단 유저는 만들어서 저장.
            User user=User.builder()
                    .id(info.getId())
                    .nickname(info.getNickname())
                    .passwd(passwordEncoder.encode(info.getPasswd()))   //decoding how?
                    //  .roles(Collections.singletonList("ROLE_USER"))
                    .role(UserRole.ROLE_PG)
                    .pg(info.getPg())
                    .photo(info.getPhoto())
                    .build();
            userRepository.save(user);

            // 2. 부모인 스튜디오 먼저 생성.
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
        }else{
            // 유저만 만들어서 저장. Role이 다르다!
            User user=User.builder()
                    .id(info.getId())
                    .nickname(info.getNickname())
                    .passwd(passwordEncoder.encode(info.getPasswd()))   //decoding how?
                  //  .roles(Collections.singletonList("ROLE_USER"))
                    .role(UserRole.ROLE_USER)
                    .pg(info.getPg())
                    .photo(info.getPhoto())
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public String signin(UserLoginPostReq loginInfo) {
        User member=userRepository.findById(loginInfo.getId())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(loginInfo.getPasswd(), member.getPasswd())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getId(), member.getRole());
    }



}
