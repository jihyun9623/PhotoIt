package com.ssafy.db.repository;

import com.ssafy.db.entity.AuthorLocation;
import com.ssafy.db.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorLocationRepository extends JpaRepository<AuthorLocation, String> {

}
