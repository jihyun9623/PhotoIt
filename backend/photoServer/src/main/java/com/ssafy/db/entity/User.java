package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.FetchType.LAZY;
@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private int idx;

    @Column(unique = true, name = "user_id")
    private String id;

    @Column(unique = true)
    private String nickname;

    @NotNull
    private String passwd;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean pg;

    private String photo;       //프로필 사진

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites  = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = LAZY)
    private MyStudio myStudio;


}
