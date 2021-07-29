package com.ssafy.api.service;

import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserDto;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public List<User>findAll(){
        List<User> members=new ArrayList<>();
        userRepository.findAll().forEach(e -> members.add(e));
        return members;
    }
}
