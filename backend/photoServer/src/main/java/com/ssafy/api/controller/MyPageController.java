package com.ssafy.api.controller;

import com.ssafy.api.request.UserReq;
import com.ssafy.api.response.MyPageGetRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@Api("MyPage Controller API V1")
@CrossOrigin(origins="http://localhost:8081", allowedHeaders="*", allowCredentials = "true")
public class MyPageController {
    private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * mypage controller 내 모든 메서드 jwt 인증 과정 거쳐야함
     */

    @ApiOperation(value = "마이페이지 회원정보 조회", notes = "마이페이지 진입시 해당 회원의 정보를 모두 불러온다")
    @GetMapping
    public BaseResponseBody getProfile(@RequestHeader(value = "Authorization") String token) {
        MyPageGetRes res = userService.getProfile(token);
        //logger.debug(res.toString());
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "회원정보 수정", notes = "회원정보를 업데이트한다")
    @PutMapping
    public BaseResponseBody updateProfile(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "수정할 회원정보", required = true) UserReq updateInfo) {
        userService.updateProfile(token, updateInfo);
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원정보를 삭제한다")
    @DeleteMapping
    public BaseResponseBody deleteUser( @RequestHeader(value = "Authorization") String token) {
        //logger.debug("회원 탈퇴 메서드 진입");
        userService.withdrawalUser(token);
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "사진작가 업그레이드", notes = "사진작가로 업그레이드 한다.")
    @GetMapping("/pg")
    public BaseResponseBody upgradePhotographer(@RequestHeader(value = "Authorization") String token) {
        String id = jwtTokenUtil.getUserInfo(token);
        boolean EnableUpgrade = userService.upgradePhotographer(id);
        if (EnableUpgrade)
            return BaseResponseBody.of(200, "Success");
        else
            return BaseResponseBody.of(401, "Cannot Upgrade Photographer");
    }

    @ApiOperation(value = "비밀번호 재확인", notes = "회원정보 수정 전 비밀번호를 재확인한다")
    @PostMapping("/passwdCheck")
    public BaseResponseBody isPasswordRight(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "확인할 아이디와 비밀번호", required = true) UserReq pwdInfo) {
        String id = jwtTokenUtil.getUserInfo(token);
        Boolean isRight = userService.isPasswordRight(id, pwdInfo.getPasswd());
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "닉네임 중복 확인", notes = "닉네임 중복 확인")
    @PostMapping("/nicknameCheck")
    public BaseResponseBody nicknameDuplicateCheck(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "확인할 닉네임", required = true) UserReq nickInfo) {
        Boolean isDuplicated = userService.nicknameDuplicateCheck(nickInfo.getNickname());    // 중복이면 true, 중복 아니면 false.
        if (!isDuplicated) {
            return BaseResponseBody.of(200, "Success");
        } else {
            return BaseResponseBody.of(401, "Duplicated");
        }
    }

    @ApiOperation(value = "프로필사진 수정", notes = "프로필 사진 수정")
    @PutMapping("/editphoto")
    public BaseResponseBody editProfilePhoto(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @ApiParam(value = "수정할 사진 url", required = true) UserReq photoInfo) {
        userService.editProfilePhoto(token, photoInfo);

        return BaseResponseBody.of(200, "Success");
    }

}
