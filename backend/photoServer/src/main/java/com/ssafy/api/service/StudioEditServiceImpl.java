/* 2021-08-02 스켈레톤 코드 작성	by.HeeJung,Lee */
/* 2021-08-03 구현	by.HeeJung,Lee */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudioEditServiceImpl implements StudioEditService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MyStudioRepository myStudioRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    LocationRepository locationRepository;

    // 닉네임과 현재 접속자를 비교하여 Edit접근여부 판단
    /**
     * @return
     * true : same user
     * false : not same user
     */
    @Override
    public boolean studioAuth(String JWT, String nickname) {
        // JWT -> user_id
        String jwt_id = utilCheckUserId(JWT);

        // user_id -> nickname, compare
        User user = userRepository.findUserById(jwt_id).orElse(null);

        // same -> true, diff -> false
        if(user != null && user.getNickname().equals(nickname)) return true;
        else return false;
    }

    // 작가의 프로필을 받아옴 (한줄소개, 지역)
    /**
     * @param JWT
     * @return
     * null : 작가가 아니거나 지역을 선택하지않아 지역이 없음
     * map<String, List<String>> : keys = location,introduce
     */
    @Override
    public StudioEditPgProfileResponseBody getPgProfile(String JWT){
        // JWT -> user_id -> MyStudio
        String jwt_id = utilCheckUserId(JWT);
        MyStudio myStudio = myStudioRepository.findByUser_Id(jwt_id);

        // 작가 스튜디오 조회 실패
        if(myStudio == null) return null;

        // 작가 지역조회
        List<Location> Locations = locationRepository.findByAuthorLocations_MyStudio_Idx(myStudio.getIdx());

        // 작가가 설정한 지역 없음
        if(Locations.size() == 0) return null;

        // 지역, 작가소개 Mapping
        String[] strings = new String[Locations.size()];
        for (int i = 0; i < Locations.size(); i++) { strings[i] = Locations.get(i).getName(); }

        StudioEditPgProfileResponseBody responseBody = new StudioEditPgProfileResponseBody();
        responseBody.setLocation(strings);
        responseBody.setIntroduce(myStudio.getProfile());

        return responseBody;
    }

    // 현재 베스트 사진들을 받아옴 (사진ID, 파일)
    @Override
    public StudioEditPhotoResponseBody getBestPhoto(String JWT) {
        // JWT -> user_id -> MyStudio -> List<Photo>
        String jwt_id = utilCheckUserId(JWT);
        List<Photo> photos = photoRepository.findByMyStudio_User_Id(jwt_id);

        // Best 사진으로 등록한 사진이 없음
        if(photos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        Map<String, List<String>> map = new HashMap<>();

        List<String> locations = new ArrayList<>();
        for (AuthorLocation x : authorLocation) { locations.add(x.getLocation().getName()); }
        List<String> introduce = new ArrayList<>();
        introduce.add(myStudio.getProfile());

        map.put("location", locations);
        map.put("introduce", introduce);

        return null;
    }

    @Override
    public Map<String, MultipartFile> getPgPhoto(String JWT) {
        return null;
    }

    @Override
    public boolean addBestPhoto(String JWT, int photo_id) {
        return false;
    }

    @Override
    public boolean updateBestPhoto(String JWT, int photo_id) {
        return false;
    }

    @Override
    public boolean delBestPhoto(String JWT, int photo_id) {
        return false;
    }

    @Override
    public boolean addPgPhoto(String JWT, List<MultipartFile> files) {
        return false;
    }

    @Override
    public boolean delPgPhoto(String JWT, int photo_id) {
        return false;
    }

    /**
     * @param JWT
     * @return
     * 0 : NotUser(or Expired Token)
     * 1 : User(Normal)
     * 2 : User(PhotoGrapher)
     */
    @Override
    public String utilCheckUserId(String JWT) {
        // TO-DO : JWT를 보고 id를 리턴하는 함수 구현 필요
        return "user_id";
    }
}
