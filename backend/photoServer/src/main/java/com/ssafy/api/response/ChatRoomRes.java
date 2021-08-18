package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@ApiModel("ChatRoomResponse")
public class ChatRoomRes {
    @ApiModelProperty(name = "채팅방 name", example = "qwe@qwe.com test_sj1@naver.com")
    String roomName;

    @ApiModelProperty(name = "대화내용")
    List<ChatRes> chatLists;

    public static ChatRoomRes of(String roomName, List<ChatRes> chatLists) {
        ChatRoomRes body = new ChatRoomRes();
        body.setRoomName(roomName);
        body.setChatLists(chatLists);
        return body;
    }
}
