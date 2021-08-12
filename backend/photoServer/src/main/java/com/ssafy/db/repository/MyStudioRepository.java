package com.ssafy.db.repository;

import com.ssafy.db.entity.Calendar;
import com.ssafy.db.entity.MyStudio;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MyStudioRepository extends JpaRepository<MyStudio, Integer> {
    List<MyStudio> findByNicknameContaining(String nickName);
    Optional<MyStudio> findByNickname(String nickName);
    MyStudio findByUser_Id(String id);

    MyStudio findByIdx(int idx);
    MyStudio findByUser_Nickname(String nickname);

    //----------------------------------------
    MyStudio findByUserIdx(int userId);

    @Transactional
    void deleteMyStudioByUser(User user);
}
