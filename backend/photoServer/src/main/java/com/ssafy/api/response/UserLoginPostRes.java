package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([SPOST] /user/signin) 요청에 대한 응답값 정의.
 */

@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class UserLoginPostRes extends  BaseResponseBody{
	@ApiModelProperty(name="JWT 인증 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String JWT;

	public static UserLoginPostRes of(Integer statusCode, String message, String JWT){
		// 1. ResponseBody 객체 res 하나 만들어서
		UserLoginPostRes res=new UserLoginPostRes();

		// 2. BaseResponseBody에서 상속받은 응답상태랑 메세지 설정해주고
		res.setStatusCode(statusCode);
		res.setMessage(message);

		// 3. 생성한 JWT 토큰도 설정해주기
		res.setJWT(JWT);
		return res;
	}
}