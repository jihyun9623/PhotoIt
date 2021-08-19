package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("ChatUsersReq")
public class ChatUsersReq {
    @ApiModelProperty(name = "user1")
    String user1;

    @ApiModelProperty(name = "user2")
    String user2;
}
