package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class ChatManage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatManage_idx")
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatList_idx")
    private ChatList chatList;

    private Long lastMsgId;

    @OneToMany(mappedBy = "chatManage", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages;
}
