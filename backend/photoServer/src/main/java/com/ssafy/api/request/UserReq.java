package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.File;
import java.util.List;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("UserRegisterPostRequest")
public class UserReq {

	@ApiModelProperty(name="유저 ID", example="hanq@naver.com")
	private String id;

	@ApiModelProperty(name="유저 PW", example="1234")
	private String passwd;

	@ApiModelProperty(name="닉네임", example="한뀨")
	private String nickname;

	@ApiModelProperty(name="작가여부", example= "true")
	private Boolean pg;

	@ApiModelProperty(name="지역", example="['서울', '인천']")
	private List<String> location;

	@ApiModelProperty(name="한 줄 소개", example="안녕하세요, 김작가입니다.")
	private String profile;

	@ApiModelProperty(name="유저 프로필 사진")
	private String photo;	// String->File로 추후 수정

	@Builder
    public UserReq(String id, String nickname, String passwd, Boolean pg, String photo,
				   List<String> location, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.passwd = passwd;
        this.pg = pg;
        this.photo = photo;
        this.location=location;
        this.profile=profile;
    }
}
