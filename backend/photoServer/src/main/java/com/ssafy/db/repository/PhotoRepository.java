package com.ssafy.db.repository;

import com.ssafy.db.entity.Photo;
import com.ssafy.db.entity.PhotoTag;
import com.ssafy.db.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByMyStudio_User_Id(String id);
}
