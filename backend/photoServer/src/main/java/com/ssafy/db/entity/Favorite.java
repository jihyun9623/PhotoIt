package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class Favorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_idx")
    private int idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;

    @Builder
    public Favorite(int idx, User user, MyStudio myStudio) {
        this.idx = idx;
        this.user = user;
        this.myStudio = myStudio;
    }
}
