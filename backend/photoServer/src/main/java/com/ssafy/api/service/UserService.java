package com.ssafy.api.service;

import com.ssafy.api.request.UserReq;
import com.ssafy.api.response.MyPageGetRes;

import java.util.List;

public interface UserService {

    public boolean isValidToken(String token);
        // 지역목록요청 : locationList
     List<String> locationList();

     // 작가 업그레이드
    boolean upgradePhotographer(String id);

    // 회원가입 완료 요청 : signUp
    void signUp(UserReq user);

    // 로그인 : signIn
    String signin(UserReq loginInfo);

    // 들어가자마자 회원정보 받아오기 get : /mypage
    MyPageGetRes getProfile(String token);

    // 비밀번호 맞는지 확인
    Boolean isPasswordRight(String id, String passwd);

    // 회원정보 수정하기 put : /mypage
    void updateProfile(UserReq updateInfo);

    // 회원탈퇴 delete : /mypage
    void withdrawalUser(String id);

    // 닉네임 중복 체크
    boolean nicknameDuplicateCheck(String nickname);

    // 아이디(=이메일) 중복 체크
    boolean idDuplicateCheck(String id);

    void testToken(String token);

    void editProfilePhoto(String token, UserReq photo);


    // 닉네임 중복 체크 post : /mypage/nicknameCheck

    // 프로필 사진 수정 put : /mypage/editphoto


//    // 닉네임 중복검사 : nameDupCheck
//    public boolean nameDupCheck();
//
//
//
//    // 로그아웃 : signOut
//    public boolean signOut();
//
//    User getUserByUserId(String userId);

}
