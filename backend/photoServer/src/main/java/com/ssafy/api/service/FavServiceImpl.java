/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.controller.Usercontroller;
import com.ssafy.api.response.FavResBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Favorite;
import com.ssafy.db.entity.MyStudio;
import com.ssafy.db.entity.Photo;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.FavRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("FavService")
public class FavServiceImpl implements FavService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    MyStudioRepository myStudioRepository;
    @Autowired
    FavRepository favRepository;
    @Autowired
    JwtTokenUtil jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(FavServiceImpl.class);

    // 토큰에서 닉네임 추출
    public String getNicknameFromToken(String token){
        String token2=token.replace("{", "").replace("}","");
        String token3=token.replace("\"", "");
        System.out.println(token.equals(token2)?"토큰 = 토큰2":"토큰 != 토큰2");
        System.out.println(token.equals(token2)?"토큰 = 토큰3":"토큰 != 토큰3");

        String id = jwtTokenProvider.getUserInfo(token);
        logger.debug(id!=null?id:"null");
        User member =userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다."));
        return member.getNickname();
    }

    // 찜 추가
    @Override
    public boolean addFav(String JWT, String userNick, String pgNick){
        // jwt, 닉네임으로 본인확인
        String jwtNickname = getNicknameFromToken(JWT);
        if(!userNick.equals(jwtNickname)) return false;

        // 유저, 마이스튜디오
        User user = userRepository.findUserByNickname(jwtNickname);
        MyStudio myStudio = myStudioRepository.findByUser_Nickname(pgNick);
        // nickname으로 user idx 리턴
        int userIdx = user.getIdx();
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudio.getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        // 일치하는 studioIdx가 없다면 list에 추가
        for(Iterator<Favorite> it = fav.iterator(); it.hasNext(); ){
            Favorite value = it.next();
            // 찜 리스트에 있음
            if(value.getMyStudio().getIdx()==studioIdx) {
                return false;
            }
        }

        // 찜 리스트에 없음
        Favorite favorite = new Favorite(0, user, myStudio);
        favRepository.save(favorite);
        return true;
    };

    // 찜 삭제
    @Override
    public boolean deleteFav(String JWT, String userNick, String pgNick){
        // jwt, 닉네임으로 본인확인
        String jwtNickname = getNicknameFromToken(JWT);
        if(!userNick.equals(jwtNickname)) return false;

        // 유저, 마이스튜디오
        User user = userRepository.findUserByNickname(userNick);
        MyStudio myStudio = myStudioRepository.findByUser_Nickname(pgNick);
        // nickname으로 user idx 리턴
        int userIdx = user.getIdx();
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudio.getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        // 일치하는 studioIdx가 있다면 list에서 삭제
        for(Iterator<Favorite> it = fav.iterator(); it.hasNext(); ){
            Favorite value = it.next();
            // 찜 리스트에 있음
            if(value.getMyStudio().getIdx()==studioIdx) {
                if(favRepository.deleteByMyStudio_Idx(studioIdx)==1) return true;
            }
        }

        return false;
    };

    // 찜 확인
    @Override
    public boolean checkFav(String JWT, String userNick, String pgNick){
        // jwt, 닉네임으로 본인확인
        String jwtNickname = getNicknameFromToken(JWT);
        if(!userNick.equals(jwtNickname)) return false;

        // nickname으로 user idx 리턴
        int userIdx = userRepository.findUserByNickname(userNick).getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudioRepository.findByUser_Nickname(pgNick).getIdx();

        if(fav.size()==0) return false;

        // 일치하는 studioIdx가 있다면 true
        for(Favorite f : fav){
            if(f.getMyStudio().getIdx()==studioIdx) return true;
        }
        return false;
    };

    // 찜 리스트 불러오기
    @Override
    public FavResBody getFavList(String userNick){
        System.out.println(userNick);
        // nickname으로 user idx 리턴
        int userIdx = userRepository.findUserByNickname(userNick).getIdx();
        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        if(fav.size()==0) return null;

        // 매핑
        Map<String,Map<String,List<String>>> favList = new HashMap<>();
        Map<String,List<String>> favlist = new HashMap<>();
        int i=0;
        for(Favorite f : fav) {
            //스튜디오 인덱스로 작가 스튜디오 정보 불러오기
            MyStudio studioInfo = myStudioRepository.findByIdx(f.getMyStudio().getIdx());
            //작가 닉네임
            String pgNick = studioInfo.getNickname();

            //전체사진
            List<Photo> allPhotos = studioInfo.getPhotos();
            List<String> best = new ArrayList<>();
            //전체사진에서 best인것 뽑기
            for(Photo p : allPhotos){
                if(p.isBest()){
                    best.add(p.getThumbnail());
                }
            }
            favlist.put(pgNick,best);
            favList.put("favList",favlist);
        }

        FavResBody favbody = new FavResBody();
        favbody.setFavList(favList);
        return favbody;
//        // 매핑
//        Map<String,List<String>> favNickList = new HashMap<>();
//        Map<String,List<List<String>>> favBestList = new HashMap<>();
//        List<String> nickList = new ArrayList<>();
//        List<List<String>> bestList = new ArrayList<>();
//        int i=0;
//        for(Favorite f : fav) {
//            //스튜디오 인덱스로 작가 스튜디오 정보 불러오기
//            MyStudio studioInfo = myStudioRepository.findByIdx(f.getMyStudio().getIdx());
//            //작가 닉네임 리스트에 추가
//            String pgNick = studioInfo.getNickname();
//            nickList.add(pgNick);
//
//            //전체사진
//            List<Photo> allPhotos = studioInfo.getPhotos();
//            List<String> best = new ArrayList<>();
//            //전체사진에서 best인것 뽑기
//            for(Photo p : allPhotos){
//                if(p.isBest()){
//                    best.add(p.getThumbnail());
//                }
//            }
//            bestList.add(best);
//            favNickList.put("pgNick",nickList);
//            favBestList.put("bestPhotos",bestList);
//        }
//
//        FavResBody favbody = new FavResBody();
//        favbody.setFavNicklist(favNickList);
//        favbody.setFavBestlist(favBestList);
//        return favbody;
    };
}
