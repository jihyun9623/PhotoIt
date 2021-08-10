package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 마이페이지 비밀번호 재확인 API ([POST] /mypage/passwordCheck) 요청에 필요한 리퀘스트 바디 정의.
 * */

@Data
@ApiModel("MyPageGetRequest")
public class MyPagePwdPostReq {
	@ApiModelProperty(name="확인할 유저 토큰 - 이 안의 id로 판단", example = "sds87d.6dsf92j39s8d7.fs98d2dsfsd")
	String jwt;

	@ApiModelProperty(name="확인할 유저 비밀번호", example = "1234")
	String passwd;
}

