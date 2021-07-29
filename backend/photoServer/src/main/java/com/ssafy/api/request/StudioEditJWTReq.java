/* 2021-07-28 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스튜디오 수정페이지 API ([GET] /studioedit/pgprofile) 요청에 필요한 리퀘스트 바디 정의.
 * 스튜디오 수정페이지 API ([GET] /studioedit/bestphoto) 요청에 필요한 리퀘스트 바디 정의.
 * 스튜디오 수정페이지 API ([GET] /studioedit/pgphoto) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudioEditJWTRequest")
public class StudioEditJWTReq {
	@ApiModelProperty(name="JWT" +
			"", example ="NEMOBAJISPONGEBOBILOVEMONDAY")
	String JWT;
}
