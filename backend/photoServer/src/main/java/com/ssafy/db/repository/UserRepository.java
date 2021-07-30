package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Mybatis에서 DAO로 불리는 DB Layer 접근자
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(String id);

    List<User>findAll();
}
