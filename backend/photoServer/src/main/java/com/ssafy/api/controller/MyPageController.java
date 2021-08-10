package com.ssafy.api.controller;

import com.ssafy.api.request.MailAuthPostReq;
import com.ssafy.api.request.MyPageGetReq;
import com.ssafy.api.request.MyPagePwdPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Location;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/mypage")
@Api("MyPage Controller API V1")
@CrossOrigin("*")
public class MyPageController {
    private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

    @Autowired
    UserService userService;

    /**
     * mypage controller 내 모든 메서드 jwt 인증 과정 거쳐야함
     * */

    @ApiOperation(value = "마이페이지 회원정보 조회", notes = "마이페이지 진입시 해당 회원의 정보를 모두 불러온다")
    @GetMapping
    public BaseResponseBody getProfile() {
        logger.debug("MyPage 진입, 회원정보 불러오기");
        userService.getProfile("한뀨");
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "회원정보 수정", notes = "회원정보를 업데이트한다")
    @PutMapping
    public BaseResponseBody updateProfile(@RequestBody @ApiParam(value="수정할 회원정보", required = true)UserRegisterPostReq updateInfo) {
        logger.debug("회원정보 수정 메서드 진입");

        // 회원가입시 받는 정보랑 비슷해서 UserRegisterPostReq형으로 받아옴.
        // 수정할 정보 : passwd, nickname(중복체크해야됨), pg, location, introduce
        userService.updateProfile(updateInfo);
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원정보를 삭제한다")
    @DeleteMapping
    public BaseResponseBody deleteUser(@RequestBody @ApiParam(value="수정할 회원정보", required = true)int idx) {
        logger.debug("회원 탈퇴 메서드 진입");
        userService.withdrawalUser("erasable@test.com");
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "사진작가 업그레이드", notes = "사진작가로 업그레이드 한다.")
    @GetMapping("/pg")
    public BaseResponseBody upgradePhotographer(){
        // 헤더에서 jwt 뜯기............
        boolean EnableUpgrade = userService.upgradePhotographer("업그레이드할 아이디 // 예시임");
        if(EnableUpgrade)
            return BaseResponseBody.of(200, "Success");
        else
            return BaseResponseBody.of(401, "Cannot Upgrade Photographer");
    }

    // 현재 안 돌아가는 거 맞음
    @ApiOperation(value = "비밀번호 재확인", notes = "회원정보 수정 전 비밀번호를 재확인한다")
    @PostMapping("/passwdCheck")
    public BaseResponseBody isPasswordRight(@RequestBody @ApiParam(value="확인할 아이디와 비밀번호", required = true)MyPagePwdPostReq pwdInfo) {
        logger.debug("password 재확인 메서드 진입");
        // jwt 안의 id를 추출해내야함!!!
        //Boolean isRight=userService.isPasswordRight(pwdInfo.getId(), pwdInfo.getPasswd());
        return BaseResponseBody.of(200, "Success");
    }








    // 들어가자마자 회원정보 받아오기 get : /mypage - clear
    // 비밀번호 맞는지도 확인하는 메서드 있어야대 post : /mypage/passwdCheck - clear
    // 회원정보 수정하기 put : /mypage
    // 회원탈퇴 delete : /mypage
    // 닉네임 중복 체크 post : /mypage/nicknameCheck
    // 프로필 사진 수정 put : /mypage/editphoto
    // 작가 업그레이드 post : /mypage/pg
}
