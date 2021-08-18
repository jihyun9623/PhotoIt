package com.ssafy.db.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String roomName; // 방이름
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}