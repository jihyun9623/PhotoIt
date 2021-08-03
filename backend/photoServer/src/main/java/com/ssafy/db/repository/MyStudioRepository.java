package com.ssafy.db.repository;

import com.ssafy.db.entity.AuthorLocation;
import com.ssafy.db.entity.MyStudio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStudioRepository extends JpaRepository<MyStudio, String> {

}
