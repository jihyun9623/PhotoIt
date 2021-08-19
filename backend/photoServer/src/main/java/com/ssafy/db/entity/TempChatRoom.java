package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class TempChatRoom {

    @Id
    @Column(name = "temp_Chat_Room_Name")
    private String roomName;

    @OneToMany(mappedBy = "tempChatRoom")
    private List<TempChatMessage> tempChatMessages = new ArrayList<>();

    @Builder
    public TempChatRoom (String roomName, List<TempChatMessage> tempChatMessages) {
        this.roomName = roomName;
        this.tempChatMessages = tempChatMessages;
    }

}
