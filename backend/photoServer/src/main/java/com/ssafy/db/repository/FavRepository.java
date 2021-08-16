package com.ssafy.db.repository;

import com.ssafy.db.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FavRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser_Idx(int idx);
    Integer deleteByMyStudio_Idx(int idx);
}
