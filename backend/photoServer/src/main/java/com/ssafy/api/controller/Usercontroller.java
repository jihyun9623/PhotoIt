package com.ssafy.api.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.api.request.MailPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
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





}
