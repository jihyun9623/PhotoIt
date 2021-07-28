package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ChatMessage {

    @Id @GeneratedValue
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
    private Boolean read;
}
