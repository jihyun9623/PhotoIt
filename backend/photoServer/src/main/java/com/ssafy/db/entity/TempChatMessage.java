package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class TempChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_Chat_Idx")
    private int idx;

    @NotNull
    private String senderName;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temp_Chat_Room_Name")
    private TempChatRoom tempChatRoom;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime sendTime;

    @Builder
    public TempChatMessage(int idx, String senderName, String message, TempChatRoom tempChatRoom, LocalDateTime sendTime) {
        this.idx = idx;
        this.senderName = senderName;
        this.message = message;
        this.tempChatRoom = tempChatRoom;
        this.sendTime = sendTime;
    }

}
