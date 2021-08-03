package com.ssafy.api.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SearchService {
    Map<Integer, String[]> photoList(String tag, String location);

    Map<String, String> profileList(String nickname, String location);

    Map<String, Integer> myStudioPhotoList(String tag, Integer id);
}
