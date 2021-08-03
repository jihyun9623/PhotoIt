package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;

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
