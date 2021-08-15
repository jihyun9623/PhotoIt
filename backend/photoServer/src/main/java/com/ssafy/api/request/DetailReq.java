package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("DetailRequest")
public class DetailReq {
    @ApiModelProperty(name = "userId", example = "ha@n.q")
    String id;

    @ApiModelProperty(name = "사진작가 닉네임", example = "hanq")
    String nickName;

    @ApiModelProperty(name = "썸네일 경로", example = "    ")
    String thumbnail;
}
