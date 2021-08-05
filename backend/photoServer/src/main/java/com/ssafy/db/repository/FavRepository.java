package com.ssafy.db.repository;

import com.ssafy.db.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findFavByUser(Integer userIdx);
}
