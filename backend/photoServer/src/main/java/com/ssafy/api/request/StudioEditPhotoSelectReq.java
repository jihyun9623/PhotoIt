/* 2021-07-28 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스튜디오 수정페이지 베스트 사진 선택 (추가 수정 삭제) 및 전체사진 삭제 API ([GET] /studioedit/bestphoto, photo) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudioEditAuthRequest")
public class StudioEditPhotoSelectReq {
	@ApiModelProperty(name="추가할 사진 ID", example="219215")
	String add_id;
	@ApiModelProperty(name="삭제할 사진 ID", example="219216")
	String del_id;
	@ApiModelProperty(name="JWT", example ="NEMOBAJISPONGEBOBILOVEMONDAY")
	String JWT;
}
