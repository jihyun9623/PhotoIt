package com.ssafy.api.service;

import com.ssafy.api.response.TagThumbNickNameRes;
import com.ssafy.api.response.ThumbPhotoIdRes;
import com.ssafy.api.response.UserProfile;
import com.ssafy.db.entity.Photo;
import com.ssafy.db.entity.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface MainPageService {

    String[] locationList();

    String[] tagList();

    UserProfile userProfile(String JWT, String id);

    List<TagThumbNickNameRes> getMainContents();

    String[] photoTagList(String thumbnail);

    String photoOrigin(String thumbnail);

    boolean isFavorite(String nickName, String userId);

    List<ThumbPhotoIdRes> thumbPhotoIds(String nickName, String thumbnail);

    void photoViewCnt(String thumbnail);
}
