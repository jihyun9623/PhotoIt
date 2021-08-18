package com.ssafy.db.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
public class ChatRoomDto implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;        //user1, user2 exam) "hanq babo"


    public static ChatRoomDto create(String user1, String user2) {
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.roomId = UUID.randomUUID().toString();
        String name;
        if(user1.compareTo(user2)>0)
            name = user2.concat(" "+user1);
        else
            name = user1.concat(" "+user2);
        chatRoom.name = name;
        return chatRoom;
    }
}