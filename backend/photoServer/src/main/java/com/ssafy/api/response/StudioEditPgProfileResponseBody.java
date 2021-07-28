/* 2021-07-27 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스튜디오 수정페이지 프로필정보 API ([GET] /studioedit/pgprofile) 요청에 필요한 리스폰스 바디 정의.
 */
@Getter
@Setter
@ApiModel("BaseResponseBody")
public class StudioEditPgProfileResponseBody extends BaseResponseBody {
	@ApiModelProperty(name="한줄 프로필", example = "안녕하세요 저는")
	String introduce;
	@ApiModelProperty(name="지역 목록", example = "서울,인천")
	String[] location;
	
	public static StudioEditPgProfileResponseBody of(Integer statusCode, String message, String introduce, String[] location) {
		StudioEditPgProfileResponseBody body = new StudioEditPgProfileResponseBody();
		body.setStatusCode(statusCode);
		body.setMessage(message);
		body.setIntroduce(introduce);
		body.setLocation(location);
		return body;
	}
}
