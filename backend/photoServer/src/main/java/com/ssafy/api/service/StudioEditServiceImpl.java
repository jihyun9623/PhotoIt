/* 2021-08-02 스켈레톤 코드 작성	by.HeeJung,Lee */
/* 2021-08-03 구현	by.HeeJung,Lee */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.common.util.Uploader;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudioEditServiceImpl implements StudioEditService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final MyStudioRepository myStudioRepository;
    private final PhotoRepository photoRepository;
    private final PhotoTagRepository photoTagRepository;
    private final TagRepository tagRepository;
    private final Uploader uploader;

    public StudioEditServiceImpl(UserRepository userRepository, LocationRepository locationRepository, MyStudioRepository myStudioRepository, PhotoRepository photoRepository, PhotoTagRepository photoTagRepository, TagRepository tagRepository, Uploader uploader) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.myStudioRepository = myStudioRepository;
        this.photoRepository = photoRepository;
        this.photoTagRepository = photoTagRepository;
        this.tagRepository = tagRepository;
        this.uploader = uploader;
    }

    // 닉네임과 현재 접속자를 비교하여 Edit접근여부 판단
    @Override
    public boolean studioAuth(String JWT, String nickname) {
        // JWT -> user_id
        String user_id = utilCheckUserId(JWT);

        // user_id -> nickname, compare
        User user = userRepository.findUserById(user_id).orElse(null);

        // same -> true, diff -> false
        return user != null && user.getNickname().equals(nickname);
    }

    // 작가의 프로필을 받아옴 (한줄소개, 지역)
    @Override
    public StudioEditPgProfileResponseBody getPgProfile(String JWT){
        // JWT -> user_id -> MyStudio
        String user_id = utilCheckUserId(JWT);
        MyStudio myStudio = myStudioRepository.findByUser_Id(user_id);

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
        String user_id = utilCheckUserId(JWT);
        List<Photo> photos = photoRepository.findByMyStudio_User_IdAndBestIsTrue(user_id);

        // Best 사진으로 등록한 사진이 없음
        if(photos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        List<String> strings = new ArrayList<>();
        List<MultipartFile> files = new ArrayList<>();

        for(Photo p : photos) {
            strings.add(Integer.toString(p.getIdx()));
            // TO-DO 파일 불러오기
            // File <- p.getOrigin()
            // MultipartFile multipartFile = null;
            // files.add(multipartFile);
        }

        StudioEditPhotoResponseBody responseBody = new StudioEditPhotoResponseBody();
        responseBody.setFiles(files);
        responseBody.setId(strings);

        return responseBody;
    }

    // 작가의 전체 사진들을 받아오기 (사진ID, 파일)
    @Override
    public StudioEditPhotoResponseBody getPgPhoto(String JWT) {
        // JWT -> user_id -> MyStudio -> List<Photo>
        String user_id = utilCheckUserId(JWT);
        List<Photo> photos = photoRepository.findByMyStudio_User_Id(user_id);

        // 등록한 사진이 없음
        if(photos.size() == 0) return null;

        // 사진ID, 사진파일 Mapping
        List<String> strings = new ArrayList<>();
        List<MultipartFile> files = new ArrayList<>();

        for(Photo p : photos) {
            strings.add(Integer.toString(p.getIdx()));
            // TO-DO 파일 불러오기 (섬네일파일로 불러오기)
            // File <- p.getOrigin()
            // MultipartFile multipartFile = null;
            // files.add(multipartFile);
        }

        StudioEditPhotoResponseBody responseBody = new StudioEditPhotoResponseBody();
        responseBody.setFiles(files);
        responseBody.setId(strings);

        return responseBody;
    }

    // 작가 베스트 사진 추가
    @Override
    public boolean addBestPhoto(String JWT, int add_id) {
        // JWT -> user_id -> MyStudio -> Photo -> best
        String user_id = utilCheckUserId(JWT);
        MyStudio myStudio = myStudioRepository.findByUser_Id(user_id);

        // 베스트 사진이 2개 이하일때 추가 가능
        if(photoRepository.countByMyStudio_IdxAndBestIsTrue(myStudio.getIdx()) <= 2) {
            Photo photo_before = photoRepository.findByIdx(add_id);
            if(photo_before == null) return false;
            Photo photo_after = new Photo(add_id, photo_before.getMyStudio(), photo_before.getViewCnt(), photo_before.getOrigin(), photo_before.getThumbnail(), true, photo_before.getUpload());

            photoRepository.save(photo_after);
        }
        return true;

    }

    // 작가 베스트 사진 수정
    @Override
    public boolean updateBestPhoto(String JWT, int add_id, int del_id) {
        // JWT -> user_id -> MyStudio -> Photo -> best
        String user_id = utilCheckUserId(JWT);

        // 본인의 사진인지 별도 확인 필요
        if(!myPhotoCheck(del_id, user_id)) return false;

        Photo photo_before = photoRepository.findByIdx(del_id);
        if(photo_before == null) return false;
        Photo photo_after = new Photo(del_id, photo_before.getMyStudio(), photo_before.getViewCnt(), photo_before.getOrigin(), photo_before.getThumbnail(), false, photo_before.getUpload());

        photoRepository.save(photo_after);

        photo_before = photoRepository.findByIdx(add_id);
        if(photo_before == null) return false;
        photo_after = new Photo(add_id, photo_before.getMyStudio(), photo_before.getViewCnt(), photo_before.getOrigin(), photo_before.getThumbnail(), true, photo_before.getUpload());

        photoRepository.save(photo_after);

        return true;
    }

    // 작가 베스트 사진 삭제
    @Override
    public boolean delBestPhoto(String JWT, int del_id) {
        // JWT -> user_id -> MyStudio -> Photo -> best
        String user_id = utilCheckUserId(JWT);

        // 본인의 사진인지 별도 확인 필요
        if(!myPhotoCheck(del_id, user_id)) return false;

        Photo photo_before = photoRepository.findByIdx(del_id);
        if(photo_before == null) return false;
        Photo photo_after = new Photo(del_id, photo_before.getMyStudio(), photo_before.getViewCnt(), photo_before.getOrigin(), photo_before.getThumbnail(), false, photo_before.getUpload());

        photoRepository.save(photo_after);

        return true;
    }

    // 작가 사진 업로드
    @Override
    @Transactional
    public boolean addPgPhoto(String JWT, List<MultipartFile> files, String[][] tags) {
        // JWT -> user_id -> MyStudio -> Photo
        String user_id = utilCheckUserId(JWT);
        MyStudio myStudio = myStudioRepository.findByUser_Id(user_id);
        List<String> UplodedFilesUUID = new ArrayList<>();

        for(int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String[] tag = tags[i];

            // TO-DO : File 업로드 하기
            for(MultipartFile x : files) {
                String URL = uploader.uploadS3Instance(x, );
            }

            // File 업로드 중 에러 발생시 롤백필요, UplodedFilesUUID를통해 사진 삭제, False 리턴

            // DB insert & File save
            String uuid;
            do {
                uuid = java.util.UUID.randomUUID().toString();
                // 랜덤생성 UUID가 겹치는 것이 있는지 확인
            } while (photoRepository.existsByOrigin(uuid));

            Photo photo = new Photo(0, myStudio, 0, uuid,  uuid, false, LocalDateTime.now());

            photoRepository.save(photo);

            // PhotoTag Table에 사진태그 추가
            for(int j = 0; j < tag.length; j++) {
                // 실존 태그인지 체크
                if(tagRepository.findByName(tag[j]) != null) {
                    PhotoTag photoTag = new PhotoTag(0, photo, tagRepository.findByName(tag[j]));
                    photoTagRepository.save(photoTag);
                }
            }
        }
        return true;
    }

    // 작가 사진 삭제
    @Override
    public boolean delPgPhoto(String JWT, int del_id) {
        // JWT -> user_id -> MyStudio -> Photo
        String user_id = utilCheckUserId(JWT);

        // 본인의 사진인지 별도 확인 필요
        if(!myPhotoCheck(del_id, user_id)) return false;

        return photoRepository.deleteByIdx(del_id) > 0;
    }

    // JWT -> user_id
    private String utilCheckUserId(String JWT) {
        // TO-DO : JWT를 보고 id를 리턴하는 함수 구현 필요
        return "user_id";
    }

    // 본인사진인지 체크
    private boolean myPhotoCheck (int photo_id, String user_id) {
        Photo temp = photoRepository.findByIdx(photo_id);
        if(temp == null) return false;

        return temp.getMyStudio().getUser().getId().equals(user_id);
    }

    private boolean fileSave (MultipartFile file, String uuid) {
        // 섬네일 따기
        // 파일 원본 저장
        // 섬네일 저장
        return false;
    }

    private boolean fileDelete (MultipartFile file, String uuid) {
        // 섬네일, 원본 삭제
        return false;
    }
}
