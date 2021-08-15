package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("ChatResponse")
public class ChatRes {
    @ApiModelProperty(name = "메세지 보낸사람", example = "hanq")
    String sender;

    @ApiModelProperty(name = "메세지 내용", example = "hello")
    String message;

    public static ChatRes of(String sender, String message) {
        ChatRes body = new ChatRes();
        body.setSender(sender);
        body.setMessage(message);
        return body;
    }
}
