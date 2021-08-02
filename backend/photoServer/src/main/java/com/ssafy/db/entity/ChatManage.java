package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ChatManage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatManage_idx")
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatList_idx")
    private ChatList chatList;

    private Long lastMsgId;

    @OneToMany(mappedBy = "chatManage", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @Builder
    public ChatManage(Long idx, ChatList chatList, Long lastMsgId, List<ChatMessage> chatMessages) {
        this.idx = idx;
        this.chatList = chatList;
        this.lastMsgId = lastMsgId;
        this.chatMessages = chatMessages;
    }
}
