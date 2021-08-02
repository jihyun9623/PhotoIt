package com.ssafy.db.repository;

import com.ssafy.db.entity.PhotoTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoTagRepository extends JpaRepository<PhotoTag, Integer> {
}
