package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@ApiModel("ChatRoomsResponse")
public class ChatRoomsRes {
    @ApiModelProperty(name = "채팅방 상대 닉네임 리스트")
    List<String> nickNames;

    public static ChatRoomsRes of(List<String> nickNames) {
        ChatRoomsRes body = new ChatRoomsRes();
        body.setNickNames(nickNames);
        return body;
    }
}
