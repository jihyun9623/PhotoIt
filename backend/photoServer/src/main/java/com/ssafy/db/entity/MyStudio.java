package com.ssafy.db.entity;

import com.ssafy.api.request.UserReq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class MyStudio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_idx")
    private int idx;

    private String profile;

    @Column(unique = true)
    private String nickname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "myStudio", cascade = CascadeType.ALL)
    private List<AuthorLocation> authorLocations = new ArrayList<>();

    @Builder
    public MyStudio(int idx, String profile, String nickname, User user, List<Photo> photos,
                    List<Calendar> calendars, List<Favorite> favorites, List<AuthorLocation> authorLocations) {
        this.idx = idx;
        this.profile = profile;
        this.nickname = nickname;
        this.user = user;
        this.photos = photos;
        this.calendars = calendars;
        this.favorites = favorites;
        this.authorLocations = authorLocations;

    // 수정할 정보 : passwd, nickname(중복체크해야됨), pg, location, introduce
    public void updateMyStudioProfile(UserReq updateInfo, User user){
        this.nickname=updateInfo.getNickname();
        this.profile=updateInfo.getProfile();
        //this.user=user;
    }

}
