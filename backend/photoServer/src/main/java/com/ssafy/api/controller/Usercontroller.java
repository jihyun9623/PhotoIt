package com.ssafy.api.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.api.request.MailAuthPostReq;
import com.ssafy.api.request.MailPostReq;
import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.MailService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api("User Controller API V1")
@CrossOrigin("*")
public class Usercontroller {
    private static final Logger logger = LoggerFactory.getLogger(Usercontroller.class);

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @ApiOperation(value = "회원 가입", notes = "회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/signup")
    public BaseResponseBody signUp(@RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
        System.out.println("signup method 진입");
        userService.signUp(registerInfo);
        return BaseResponseBody.of(200, "Success");
    }

    @ApiOperation(value = "로그인", notes = "로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/signin")
    public UserLoginPostRes signin(@RequestBody @ApiParam(value = "로그인 정보", required = true)UserLoginPostReq loginInfo){
        System.out.println("login method 진입");
        System.out.println(loginInfo.getId()+" "+loginInfo.getPasswd());
        String jwt = userService.signin(loginInfo);
        return UserLoginPostRes.of(200, "Success", jwt, loginInfo.getId());
    }

    @ApiOperation(value = "이메일 인증", notes = "회원가입시 이메일 인증한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/emailauth")
    public BaseResponseBody emailAuth(@RequestBody @ApiParam(value="인증할 이메일 정보", required = true)MailPostReq authinfo){
        System.out.println("email auth method 진입");
        System.out.println(authinfo);
        // 이거 id만 들어오는데 알아서 id만 받나?
        boolean isAuthorization = mailService.emailAuth(authinfo);
        if(isAuthorization){
            return BaseResponseBody.of(200, "Success");
        }
        return BaseResponseBody.of(401, "Authorization Fail");
    }

    @ApiOperation(value = "이메일 인증", notes = "회원가입시 이메일 인증한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/emailauthcheck")
    public BaseResponseBody emailAuthCheck(@RequestBody @ApiParam(value="확인할 인증코드와 아이디", required = true) MailAuthPostReq authinfo){
        System.out.println("email auth check method 진입");
        // 이거 id만 들어오는데 알아서 id만 받나?
        boolean isAuthorization = mailService.emailAuthCheck(authinfo);
        if(isAuthorization){
            return BaseResponseBody.of(200, "Success");
        }
        return BaseResponseBody.of(401, "Authorization Fail");
    }


}
