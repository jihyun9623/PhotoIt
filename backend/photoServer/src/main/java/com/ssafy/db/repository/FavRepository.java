package com.ssafy.db.repository;

import com.ssafy.db.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser_Idx(int userIdx);
    boolean deleteByMyStudio_Idx(int studioIdx);
}
