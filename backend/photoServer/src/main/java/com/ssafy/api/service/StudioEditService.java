/* 2021-08-02 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface StudioEditService {

    boolean studioAuth(String JWT, String nickname);

    StudioEditPgProfileResponseBody getPgProfile(String JWT);

    StudioEditPhotoResponseBody getBestPhoto(String JWT);

    Map<String, MultipartFile> getPgPhoto(String JWT);

    boolean addBestPhoto(String JWT, int photo_id);

    boolean updateBestPhoto(String JWT, int photo_id);

    boolean delBestPhoto(String JWT, int photo_id);

    boolean addPgPhoto(String JWT, List<MultipartFile> files);

    boolean delPgPhoto(String JWT, int photo_id);

    String utilCheckUserId(String JWT);
}
