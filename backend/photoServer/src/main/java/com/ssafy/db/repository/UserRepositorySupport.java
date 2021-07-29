package com.ssafy.db.repository;

import com.ssafy.db.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class UserRepositorySupport{
//    QUser qUser = QUser.user;
//
//    public Optional<User> findUserByUserId(String userId) {
//        User user = jpaQueryFactory.select(qUser).from(qUser)
//                .where(qUser.userId.eq(userId)).fetchOne();
//        if(user == null) return Optional.empty();
//        return Optional.ofNullable(user);
//    }
    @Autowired
    UserRepository userRepository;

}
