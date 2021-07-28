/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스튜디오 수정페이지 유저 확인 API ([GET] /studioedit/studioauth) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudioEditAuthRequest")
public class StudioEditAuthReq {
	@ApiModelProperty(name="유저 닉네임", example="Lorem_Ipsum")
	String nickname;
	@ApiModelProperty(name="JWT", example ="NEMOBAJISPONGEBOBILOVEMONDAY")
	String JWT;
}
