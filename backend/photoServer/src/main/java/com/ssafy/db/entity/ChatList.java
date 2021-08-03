package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class ChatList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatList_idx")
    private Long idx;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    private int pairIdx;

    @OneToOne(mappedBy = "chatList", fetch = LAZY)
    private ChatManage chatManage;

    @Builder
    public ChatList(Long idx, Long roomId, User user, int pairIdx, ChatManage chatManage) {
        this.idx = idx;
        this.roomId = roomId;
        this.user = user;
        this.pairIdx = pairIdx;
        this.chatManage = chatManage;
    }
}
