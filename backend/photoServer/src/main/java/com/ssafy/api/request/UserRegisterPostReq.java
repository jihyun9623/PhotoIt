package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {

	@ApiModelProperty(name="유저 ID", example="test@ssafy.com")
	private String email;

	@ApiModelProperty(name="유저 PW", example="1234")
	private String passwd;

	@ApiModelProperty(name="닉네임", example="김작가")
	private String nickname;

	@ApiModelProperty(name="작가여부", example="true/false")
	private Boolean pg;

	@ApiModelProperty(name="지역", example="[인천, 서울]")
	private List<String> location;

	@ApiModelProperty(name="한 줄 소개", example="안녕하세요, 김작가입니다.")
	private String introduce;

	@ApiModelProperty(name="유저 프로필")
	private File file;

}
