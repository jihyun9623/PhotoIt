package com.ssafy.api.service;

import com.ssafy.api.request.UserReq;
import com.ssafy.api.response.MyPageGetRes;
import com.ssafy.common.model.response.BaseResponseBody;

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

    // 로그아웃 : signOut
    BaseResponseBody signOut(String token);

    // 들어가자마자 회원정보 받아오기 get : /mypage
    MyPageGetRes getProfile(String token);

    // 비밀번호 맞는지 확인
    Boolean isPasswordRight(String id, String passwd);

    // 회원정보 수정하기 put : /mypage
    void updateProfile(String token, UserReq updateInfo);

    // 회원탈퇴 delete : /mypage
    void withdrawalUser(String token);

    // 닉네임 중복 체크
    boolean nicknameDuplicateCheck(String nickname);

    // 아이디(=이메일) 중복 체크
    boolean idDuplicateCheck(String id);

    void testToken(String token);

    void editProfilePhoto(String token, UserReq photo);


//    // 로그아웃 : signOut
//    public boolean signOut();

}
