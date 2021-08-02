package com.ssafy.db.repository;

import com.ssafy.db.entity.Photo;
import com.ssafy.db.entity.PhotoTag;
import com.ssafy.db.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}