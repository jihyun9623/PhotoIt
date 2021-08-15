package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@ApiModel("ChatRoomResponse")
public class ChatRoomRes {
    @ApiModelProperty(name = "채팅방 id", example = "84e06280-e302-40cb-b639-9fcf8155006b")
    String roomId;

    @ApiModelProperty(name = "대화내용")
    List<ChatRes> chatLists;

    public static ChatRoomRes of(String roomId, List<ChatRes> chatLists) {
        ChatRoomRes body = new ChatRoomRes();
        body.setRoomId(roomId);
        body.setChatLists(chatLists);
        return body;
    }
}
