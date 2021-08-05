package com.ssafy.db.entity;

import com.ssafy.api.request.UserRegisterPostReq;
import io.jsonwebtoken.lang.Assert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.FetchType.LAZY;
@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private int idx;

    @NotNull
    @Column(unique = true, name = "user_id")
    private String id;

    @NotNull
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

    @Builder
    public User(int idx, String id, String nickname, String passwd, Boolean pg, String photo,
                List<Favorite> favorites, MyStudio myStudio) {
        this.idx = idx;
        this.id = id;
        this.nickname = nickname;
        this.passwd = passwd;
        this.pg = pg;
        this.photo = photo;
        this.favorites = favorites;
        this.myStudio = myStudio;
    }


}
