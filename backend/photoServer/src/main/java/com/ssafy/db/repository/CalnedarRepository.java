package com.ssafy.db.repository;

import com.ssafy.db.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalnedarRepository extends JpaRepository<Calendar, Integer> {
    List<Calendar> findCal(Integer studioIdx);
}
