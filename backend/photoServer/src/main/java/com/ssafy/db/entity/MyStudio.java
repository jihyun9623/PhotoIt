package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class MyStudio {

    @Id @GeneratedValue
    @Column(name = "studio_idx")
    private int idx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String profile;

    @Column(unique = true)
    private String nickname;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<AuthorLocation> authorLocations;
}
