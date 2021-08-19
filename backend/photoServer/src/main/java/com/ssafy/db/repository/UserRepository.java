package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Mybatis에서 DAO로 불리는 DB Layer 접근자
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(String id);
}
