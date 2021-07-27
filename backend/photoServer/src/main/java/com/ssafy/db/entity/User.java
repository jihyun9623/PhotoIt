package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private int idx;

    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String nickname;

    @NotNull
    private String passwd;

    @NotNull
    private Boolean pg;

    private String photo;       //프로필 사진


}
