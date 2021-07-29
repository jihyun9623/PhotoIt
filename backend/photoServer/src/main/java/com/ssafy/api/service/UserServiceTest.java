package com.ssafy.api.service;

import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserDto;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void findAll(){
        List<User> members=new ArrayList<>();
        userRepository.findAll().forEach(e -> members.add(e));
        for(User u:members){
            System.out.println(u);
        }
       // return members;
    }
}
