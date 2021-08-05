package com.ssafy.db.repository;

import com.ssafy.db.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    List<Calendar> findByMyStudio_Idx(int idx);
}
