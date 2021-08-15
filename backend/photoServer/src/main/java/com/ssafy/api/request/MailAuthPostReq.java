package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("MailAuthRequest")
public class MailAuthPostReq {
    @ApiModelProperty(name = "수신자 주소", example = "sr5871@gmail.com")
    String address;
    @ApiModelProperty(name = "메일 제목", example = "Photo-it 이메일 인증")
    String title;
    @ApiModelProperty(name = "메일 제목", example = "Photo-it 인증 메일입니다.")
    String msg;
}
