package com.ssafy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ArrayList<String> locationList() {
        return null;
    }

    @Override
    public boolean emailAuth() {
        return false;
    }

    @Override
    public boolean emailAuthCheck() {
        return false;
    }

    @Override
    public boolean nameDupCheck() {
        return false;
    }

    @Override
    public boolean signUp() {
        return false;
    }

    @Override
    public boolean signIn() {
        return false;
    }

    @Override
    public boolean signOut() {
        return false;
    }
    // modelMapper.map(src, dest);
}
