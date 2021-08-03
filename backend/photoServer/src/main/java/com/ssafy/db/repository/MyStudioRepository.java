package com.ssafy.db.repository;

import com.ssafy.db.entity.MyStudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyStudioRepository extends JpaRepository<MyStudio, Integer> {
    List<MyStudio> findByNicknameContaining(String nickName);
}
