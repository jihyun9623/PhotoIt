package com.ssafy.api.service;

import com.ssafy.api.response.PhotoIdPhotoRes;
import com.ssafy.api.response.PhotoIdThumbNickNameRes;
import com.ssafy.api.response.ProfileNickNameRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SearchService {
    List<PhotoIdThumbNickNameRes> photoList(String tag, String location);

    List<ProfileNickNameRes> profileList(String nickname, String location);

    List<PhotoIdPhotoRes> myStudioPhotoList(String tag, Integer id);
}
