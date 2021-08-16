/* 2021-08-02 스켈레톤 코드 작성	by.HeeJung,Lee */
/* 2021-08-03 구현	by.HeeJung,Lee */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.common.util.Uploader;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    JwtTokenUtil jwtTokenProvider;

    private final Uploader uploader;
    private final String DirNameOrigin = "origin";
    private final String DirNameThumbnail = "thumbnail";
    private final static String TEMP_FILE_PATH = "classpath:";

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
        List<String> files = new ArrayList<>();

        for(Photo p : photos) {
            strings.add(Integer.toString(p.getIdx()));
            files.add(p.getOrigin());
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
        List<String> files = new ArrayList<>();

        for(Photo p : photos) {
            strings.add(Integer.toString(p.getIdx()));
            files.add(p.getThumbnail());
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

        // 여러 사진파일 업로드
        for(int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String[] tag = tags[i];
            String originURL = null;
            String thumbnailURL = null;

            try {
                originURL = uploader.uploadS3Instance(file, DirNameOrigin);

                /* 섬네일 추출 */
                File thumbnail = new File(TEMP_FILE_PATH + "thumbnail");
                // QUALITY 1~5 -> 4, resizeMode = Auto, size = 400 * 400, Filename = "thumbnail_" + OriginalName
                ImageIO.write(Scalr.resize(ImageIO.read(file.getInputStream()), Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 400,400), file.getContentType(), thumbnail);

                thumbnailURL = uploader.uploadS3Instance(thumbnail, DirNameThumbnail);
            } catch (IOException e) {
                return false;
            }

            // DB insert
            Photo photo = new Photo(0, myStudio, 0, originURL,  thumbnailURL, false, LocalDateTime.now());

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

        Photo photo = photoRepository.findByIdx(del_id);

        if(photo != null) {
            String origin = photo.getOrigin();
            String thumbnail = photo.getThumbnail();
            if(photoRepository.deleteByIdx(del_id) > 0) {
                uploader.deleteS3Instance(origin);
                uploader.deleteS3Instance(thumbnail);
                return true;
            }
        }
        return false;
    }

    // JWT -> user_id
    private String utilCheckUserId(String JWT) {
        // TO-DO : JWT를 보고 id를 리턴하는 함수 구현 필요
        String id=jwtTokenProvider.getUserInfo(JWT);
        return id;
    }

    // 본인사진인지 체크
    private boolean myPhotoCheck (int photo_id, String user_id) {
        Photo temp = photoRepository.findByIdx(photo_id);
        if(temp == null) return false;

        return temp.getMyStudio().getUser().getId().equals(user_id);
    }
}
