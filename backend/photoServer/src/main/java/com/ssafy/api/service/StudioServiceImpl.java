/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioGetPhotosResBody;
import com.ssafy.api.response.StudioPgProfileResBody;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {
    @Autowired
    UserRepository userRepository;
    MyStudioRepository myStudioRepository;
    CalnedarRepository calnedarRepository;
    PhotoRepository photoRepository;
    LocationRepository locationRepository;

    @Override
    public Integer getStudioIdx(String nickname) {
        //studio idx 리턴
        return 123;
    }

    //작가 프로필 가져오기
    @Override
    public StudioPgProfileResBody getPgProfile(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        Integer studio_idx = getStudioIdx(nickname);

        // 스튜디오 idx로 작가 프로필을 가져옴
        MyStudio myStudio = myStudioRepository.findUserByStudio_Idx(studio_idx);

        // 지역
        List<Location> loc = locationRepository.findByAuthorLocations_MyStudio_Idx(studio_idx);
        if(loc.size() == 0) return null;

        //지역,작가소개 매핑
        String[] strings = new String[loc.size()];
        for (int i = 0; i < loc.size(); i++) { strings[i] = loc.get(i).getName(); }

        StudioPgProfileResBody resbody = new StudioPgProfileResBody();
        resbody.setLocation(strings);
        resbody.setIntroduce(myStudio.getProfile());

        return resbody;
    };

    //일정 가져오기
    @Override
    public LocalDateTime[] showCalendar(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        Integer studio_idx = getStudioIdx(nickname);

        // 스튜디오 idx로 일정 리스트를 가져옴
        List<Calendar> cal = calnedarRepository.findCal(studio_idx);

        // 일정이 없음
        if(cal.size() == 0) return null;

        LocalDateTime[] calList = new LocalDateTime[cal.size()];
        int i=0;
        for(Calendar c : cal) calList[i++] = c.getDate();

        return calList;
    };

    //일정 수정
    @Override
    public boolean editCalendar(String nickname, String JWT, LocalDateTime[] cal_time){
        // 닉네임, JWT로 본인 확인 -> 마이스튜디오 idx 받아옴 -> 일정 리스트 받아옴

        // JWT를 보고 닉네임 받아오는 부분 구현 필요!!! //
        String jwtNickname = "";

        Integer studio_idx = getStudioIdx(nickname);
        MyStudio mystudio = myStudioRepository.findUserByStudio_Idx(studio_idx);

        if(nickname!=jwtNickname) return false;

        // DB에 일정 추가하기
        for(int i=0;i<cal_time.length;i++){
            Calendar calList = new Calendar(0, cal_time[i],mystudio);
            calnedarRepository.save(calList);
        }

        return true;
    };

    //베스트 사진 가져오기
    @Override
    public StudioGetPhotosResBody getBestPhotos(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        Integer studio_idx = getStudioIdx(nickname);

        // 스튜디오 idx로 베스트 사진 접근
        List<Photo> bPhotos = photoRepository.findByStudioIdxAndBestIsTrue(studio_idx);

        // Best 사진으로 등록한 사진이 없음
        if(bPhotos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        String[] pid = new String[bPhotos.size()];
        List<MultipartFile> files = new ArrayList<>();

        int i=0;
        for(Photo p : bPhotos) {
            pid[i++] = Integer.toString(p.getIdx());
            // TO-DO 파일 불러오기
            // File <- p.getOrigin()
            // MultipartFile multipartFile = null;
            // files.add(multipartFile);
        }

        StudioGetPhotosResBody resbody = new StudioGetPhotosResBody();
        resbody.setPid(pid);
        resbody.setFileList(files);

        return resbody;
    };

    //작가 전체 사진 가져오기
    @Override
    public StudioGetPhotosResBody getAllPgPhotos(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        Integer studio_idx = getStudioIdx(nickname);

        // 스튜디오 idx로 전체 사진 접근
        List<Photo> photos = photoRepository.findByStudioIdx(studio_idx);

        // 작가 사진이 없음
        if(photos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        String[] pid = new String[photos.size()];
        List<MultipartFile> files = new ArrayList<>();

        int i=0;
        for(Photo p : photos) {
            pid[i++] = Integer.toString(p.getIdx());
            // TO-DO 파일 불러오기
            // File <- p.getOrigin()
            // MultipartFile multipartFile = null;
            // files.add(multipartFile);
        }

        StudioGetPhotosResBody resbody = new StudioGetPhotosResBody();
        resbody.setPid(pid);
        resbody.setFileList(files);

        return resbody;
    };
}
