package com.ssafy.db.repository;

import com.ssafy.db.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Photo findByIdx(int idx);

    List<Photo> findByMyStudio_User_Id(String id);

    List<Photo> findByMyStudio_User_IdAndBestIsTrue(String id);

    boolean existsByOrigin(String origin);

    long deleteByIdx(int idx);

    long countByMyStudio_IdxAndBestIsTrue(int idx);

    @Modifying
    @Query("update Photo set Photo.best = :best where idx = :id")
    Boolean updateBestPhoto(int id, boolean best);

}
