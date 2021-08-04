package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface UserService {

    // 회원가입 완료 요청 : signUp
    public void signUp(UserRegisterPostReq user);

    // 로그인 : signIn
    public boolean signIn();

//    // 지역목록요청 : locationList
//    public ArrayList<String> locationList();

//    // 이메일 인증 요청 : emailAuth
//    public boolean emailAuth();
//
//    // 이메일 코드 확인 요청 : emailAuthCheck
//    public boolean emailAuthCheck();
//
//    // 닉네임 중복검사 : nameDupCheck
//    public boolean nameDupCheck();
//
//
//
//    // 로그아웃 : signOut
//    public boolean signOut();
//
//    User getUserByUserId(String userId);

    // 회원정보 받아오기 : getMyInfo

    // 닉네임 중복체크 : nameDupCheck >>good
    // 회원정보 수정 : editMyInfo
    // 작가 업그레이드 : upgradeToPg
    // 회원탈퇴 : quitUser
}
