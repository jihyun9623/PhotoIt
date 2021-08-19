package com.ssafy.api.service;

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

    User getUser(String JWT, String id);

    Map<String, Map<String, String>> getMainContents();
}
