/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioGetPhotosResBody;
import com.ssafy.api.response.StudioPgProfileResBody;
import com.ssafy.common.util.JwtTokenUtil;
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

    private final MyStudioRepository myStudioRepository;
    private final CalendarRepository calendarRepository;
    private final PhotoRepository photoRepository;
    private final LocationRepository locationRepository;
    @Autowired
    JwtTokenUtil jwtTokenProvider;
    @Autowired
    UserRepository userRepository;

    public StudioServiceImpl(MyStudioRepository myStudioRepository, CalendarRepository calendarRepository, PhotoRepository photoRepository, LocationRepository locationRepository) {
        this.myStudioRepository = myStudioRepository;
        this.calendarRepository = calendarRepository;
        this.photoRepository = photoRepository;
        this.locationRepository = locationRepository;
    }

    //작가 프로필 가져오기
    @Override
    public StudioPgProfileResBody getPgProfile(String nickname){
        try {
            // 닉네임으로 스튜디오 idx를 가져옴
            int studioIdx = myStudioRepository.findByUser_Nickname(nickname).getIdx();

            // 스튜디오 idx로 작가 프로필을 가져옴
            MyStudio myStudio = myStudioRepository.findByIdx(studioIdx);

            // 지역
            List<Location> loc = locationRepository.findByAuthorLocations_MyStudio_Idx(studioIdx);

            //지역,작가소개 매핑
            String[] strings = new String[loc.size()];
            for (int i = 0; i < loc.size(); i++) { strings[i] = loc.get(i).getName(); }

            StudioPgProfileResBody resbody = new StudioPgProfileResBody();
            resbody.setLocation(strings);
            resbody.setIntroduce(myStudio.getProfile());
            return resbody;
        }catch (NullPointerException e){
            return null;
        }
    };

    //일정 가져오기
    @Override
    public String[] showCalendar(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        int studioIdx = myStudioRepository.findByUser_Nickname(nickname).getIdx();

        // 스튜디오 idx로 일정 리스트를 가져옴
        List<Calendar> cal = calendarRepository.findByMyStudio_Idx(studioIdx);

        // 일정이 없음
        if(cal.size() == 0) return null;

        String[] calList = new String[cal.size()];
        int i=0;
        for(Calendar c : cal) calList[i++] = c.getDate();

        return calList;
    };

    // 토큰에서 닉네임 추출
    public String getNicknameFromToken(String token){
        String id=jwtTokenProvider.getUserInfo(token);
        User member =userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다."));
        return member.getNickname();
    }

    //일정 추가
    @Override
    public boolean addCalendar(String nickname, String JWT, String[] cal_time){
        // 닉네임, JWT로 본인 확인 -> 마이스튜디오 idx 받아옴 -> 일정 추가

        // JWT를 보고 닉네임 받아오는 부분 구현 필요!!! //
        String jwtNickname = getNicknameFromToken(JWT);

        // 닉네임으로 스튜디오 idx를 가져옴
        int studioIdx = myStudioRepository.findByUser_Nickname(nickname).getIdx();
        MyStudio mystudio = myStudioRepository.findByIdx(studioIdx);

        if(!nickname.equals(jwtNickname)) return false;

        try{
            // DB에 일정 추가하기
            for(int i=0;i<cal_time.length;i++){
                Calendar calList = new Calendar(0, cal_time[i],mystudio);
                calendarRepository.save(calList);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    };

    //일정 삭제
    @Override
    public boolean deleteCalendar(String nickname, String JWT, String[] cal_time){
        // 닉네임, JWT로 본인 확인 -> 마이스튜디오 idx 받아옴 -> 일정 삭제

        // JWT를 보고 닉네임 받아오는 부분 구현 필요!!! //
        String jwtNickname = getNicknameFromToken(JWT);

        if(!nickname.equals(jwtNickname)) return false;

        // 일정 삭제하기
        for(int i=0;i<cal_time.length;i++){
            if(calendarRepository.deleteByDate(cal_time[i])==1) return true;
        }

        return false;
    };

    //베스트 사진 가져오기
    @Override
    public StudioGetPhotosResBody getBestPhotos(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        int studioIdx = myStudioRepository.findByUser_Nickname(nickname).getIdx();

        // 스튜디오 idx로 베스트 사진 접근
        List<Photo> bPhotos = photoRepository.findByMyStudio_IdxAndBestIsTrue(studioIdx);

        // Best 사진으로 등록한 사진이 없음
        if(bPhotos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        String[] pid = new String[bPhotos.size()];
        String[] borigin = new String[bPhotos.size()];

        int i=0;
        for(Photo p : bPhotos) {
            pid[i] = Integer.toString(p.getIdx());
            borigin[i] = p.getOrigin();
            i++;
        }

        StudioGetPhotosResBody resbody = new StudioGetPhotosResBody();
        resbody.setPid(pid);
        resbody.setOrigin(borigin);

        return resbody;
    };

    //작가 전체 사진 가져오기
    @Override
    public StudioGetPhotosResBody getAllPgPhotos(String nickname){
        // 닉네임으로 스튜디오 idx를 가져옴
        int studioIdx = myStudioRepository.findByUser_Nickname(nickname).getIdx();

        // 스튜디오 idx로 전체 사진 접근
        List<Photo> photos = photoRepository.findByMyStudio_Idx(studioIdx);

        // 작가 사진이 없음
        if(photos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        String[] pid = new String[photos.size()];
        String[] origin = new String[photos.size()];

        int i=0;
        for(Photo p : photos) {
            if(p==null) break;
            pid[i] = Integer.toString(p.getIdx());
            origin[i] = p.getOrigin();
            i++;
        }

        StudioGetPhotosResBody resbody = new StudioGetPhotosResBody();
        resbody.setPid(pid);
        resbody.setOrigin(origin);

        return resbody;
    };
}
