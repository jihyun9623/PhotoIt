package com.ssafy.db.repository;

import com.ssafy.db.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    List<Calendar> findByMyStudio_Idx(int idx);
    Integer deleteByDate(String date);
}
