package com.ssafy.api.request;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//
///**
// * 유저 로그인 API ([POST] /api/v1/auth/login) 요청에 필요한 리퀘스트 바디 정의.
// */
//@Getter
//@Setter
//@ApiModel("UserLoginPostRequest")
//public class UserLoginPostReq {
//	@ApiModelProperty(name="유저 ID", example="test")
//	String id;
//	@ApiModelProperty(name="유저 Password", example="1234")
//	String password;
//}

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 마이페이지 API ([GET] /mypage) 요청에 필요한 리퀘스트 바디 정의.
 * */

@Data
@ApiModel("MyPageGetRequest")
public class MyPageGetReq {
	@ApiModelProperty(name="JWT 인증 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String jwt;

	@ApiModelProperty(name="유저 닉네임", example = "한뀨")
	String nickname;
}

