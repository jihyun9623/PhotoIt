package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Long msgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatManage chatManage;

    @NotNull
    private String sender;

    private String content;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isRead;

    @Builder
    public ChatMessage(Long msgId, ChatManage chatManage, String sender, String content, LocalDateTime date, Boolean isRead) {
        this.msgId = msgId;
        this.chatManage = chatManage;
        this.sender = sender;
        this.content = content;
        this.date = date;
        this.isRead = isRead;
    }
}
