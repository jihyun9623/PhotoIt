package com.ssafy.db.repository;

import com.ssafy.db.entity.Location;
import com.ssafy.db.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TagRepository extends JpaRepository<Tag, String> {

}
