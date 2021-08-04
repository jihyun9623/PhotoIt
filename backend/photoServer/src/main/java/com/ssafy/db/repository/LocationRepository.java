package com.ssafy.db.repository;

import com.ssafy.db.entity.Location;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, String> {
    List<Location> findByAuthorLocations_MyStudio_Idx(int idx);

public interface LocationRepository extends JpaRepository<Location, String> {
    Location findLocationByName(String name);
}
