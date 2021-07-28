package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Favorite {

    @Id @GeneratedValue
    @Column(name = "favorite_idx")
    private int idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;


}
