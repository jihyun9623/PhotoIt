package com.ssafy.api.service;

import com.ssafy.db.entity.Location;
import com.ssafy.db.entity.Photo;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.entity.User;

import javax.swing.*;
import java.util.List;

public interface MainPageService {

    List<Location> locationList();

    List<Tag> tagList();

    User getUser(String JWT);

    List<Photo> getMainContents();
}
