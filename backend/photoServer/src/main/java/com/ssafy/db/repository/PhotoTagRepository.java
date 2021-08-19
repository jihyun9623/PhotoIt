package com.ssafy.db.repository;

import com.ssafy.db.entity.PhotoTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoTagRepository extends JpaRepository<PhotoTag, Integer> {
    long deleteByPhoto_Idx(int idx);
}
