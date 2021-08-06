package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("MailRequest")
public class MailPostReq {
    @ApiModelProperty(name = "인증할 id", example = "sr5871@gmail.com")
    String id;
    @ApiModelProperty(name = "인증코드", example = "E5039A23")
    String code;
}
