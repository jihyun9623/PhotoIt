package com.ssafy.db.repository;

import com.ssafy.db.entity.AuthorLocation;
import com.ssafy.db.entity.Location;
import com.ssafy.db.entity.MyStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AuthorLocationRepository extends JpaRepository<AuthorLocation, String> {
      List<AuthorLocation> findAuthorLocationByMyStudio(MyStudio myStudio);

      @Transactional
      void deleteAuthorLocationByMyStudio(MyStudio myStudio);

//    MyStudio findByUser_Id(String id);
}
