package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.LocationGetRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.MailService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletResponse;

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

    @ApiOperation(value = "지역 목록 요청", notes = "지역 목록을 불러온다.")
    @GetMapping("/location")
    public LocationGetRes getLocation() {
        logger.debug("getLocation 진입, 지역목록 불러오기");
        return LocationGetRes.of(200, "Success", userService.locationList());
    }


    @ApiOperation(value = "회원 가입", notes = "회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/signup")
    public BaseResponseBody signUp(@RequestBody @ApiParam(value = "회원가입 정보", required = true) UserReq registerInfo) {
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
    public ResponseEntity<UserLoginPostRes> signin(@RequestBody @ApiParam(value = "로그인 정보", required = true)UserReq loginInfo, HttpServletResponse response){
        logger.debug("login Method 진입");
        logger.debug("들어온 loginInfo : "+loginInfo.getId()+" / "+loginInfo.getPasswd());
        //
        String jwt = userService.signin(loginInfo);
        logger.debug("생성된 jwt : "+jwt);
        logger.debug("-------------------------------");
        //
        userService.testToken(jwt);
        UserLoginPostRes res=UserLoginPostRes.of(200, "Success", jwt, loginInfo.getId());
        response.setHeader("Authorization", jwt);
        return new ResponseEntity<UserLoginPostRes>(res, HttpStatus.OK);
        //return UserLoginPostRes.of(200, "Success", jwt, loginInfo.getId());
    }


    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "JWT token", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value="로그아웃", notes="로그아웃")
    @GetMapping("/signout")
    public BaseResponseBody signOut( @RequestHeader(value = "Authorization") String token){
        token=token.split(" ")[1];
        logger.debug("로그아웃 메서드 진입");
        return userService.signOut(token);
    }


    @ApiOperation(value = "닉네임 중복 확인", notes = "닉네임 중복 확인")
    @PostMapping("/nicknameCheck")
    public BaseResponseBody nicknameDuplicateCheck( @RequestBody @ApiParam(value="확인할 닉네임", required = true) UserReq nickInfo) {
        Boolean isDuplicated=userService.nicknameDuplicateCheck(nickInfo.getNickname());    // 중복이면 true, 중복 아니면 false.
        if(!isDuplicated) {
            return BaseResponseBody.of(200, "Success");
        }else{
            return BaseResponseBody.of(401, "Duplicated");
        }

    }


    @ApiOperation(value = "아이디(=이메일) 중복 확인", notes = "아이디(=이메일) 중복 확인")
    @PostMapping("/emaildup")
    public BaseResponseBody idDuplicateCheck( @RequestBody @ApiParam(value="확인할 아이디(=이메일)", required = true) UserReq idInfo) {
        logger.debug(idInfo.getId());
        Boolean isDuplicated=userService.idDuplicateCheck(idInfo.getId());    // 중복이면 true, 중복 아니면 false.
        if(!isDuplicated) {
            return BaseResponseBody.of(200, "Success");
        }else{
            logger.debug("아이디(=이메일) 중복!");
            return BaseResponseBody.of(401, "Duplicated");
        }
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
        boolean isAuthorization = mailService.emailAuth(authinfo);
        if(isAuthorization){
            return BaseResponseBody.of(200, "Success");
        }
        return BaseResponseBody.of(401, "Authorization Fail");
    }


    @ApiOperation(value = "이메일 인증", notes = "회원가입시 이메일 인증한다.")
    @PostMapping("/emailAuthCheck")
    public BaseResponseBody emailAuthCheck(@RequestBody @ApiParam(value="확인할 인증코드와 아이디", required = true) MailPostReq authinfo) throws AuthenticationFailedException {
        System.out.println("email auth check method 진입");
        mailService.emailAuthCheck(authinfo);
        return BaseResponseBody.of(200, "Success");
    }


}
