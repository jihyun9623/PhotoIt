package com.ssafy.db.repository;

import com.ssafy.db.entity.MyStudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyStudioRepository extends JpaRepository<MyStudio, Integer> {
    List<MyStudio> findByNicknameContaining(String nickName);
    Optional<MyStudio> findByNickname(String nickName);
    MyStudio findByUser_Id(String id);
<<<<<<< HEAD
=======
    MyStudio findUserByStudio_Idx(Integer studio_idx);
>>>>>>> origin/BEfeat_Studio
}