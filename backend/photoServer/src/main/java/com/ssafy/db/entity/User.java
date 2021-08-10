package com.ssafy.db.entity;

import com.ssafy.api.request.UserRegisterPostReq;
import io.jsonwebtoken.lang.Assert;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.file.attribute.AclEntry;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;
@Entity
@Getter
@RequiredArgsConstructor
public class User implements UserDetails{

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

    @Column(name = "role")
    private String role;

    // add security roles
//    @ElementCollection(fetch = FetchType.EAGER)
    //@Builder.Default
//    private List<String> roles = new ArrayList<>();
//    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
//    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

//    @Builder
//    public User(int idx, String id, String nickname, String passwd, Boolean pg, String photo,
//                List<Favorite> favorites, MyStudio myStudio,UserRole role) {
//        this.idx = idx;
//        this.id = id;
//        this.nickname = nickname;
//        this.passwd = passwd;
//        this.pg = pg;
//        this.photo = photo;
//        this.favorites = favorites;
//        this.myStudio = myStudio;
//        this.role=role;
//    }
    @Builder
    public User(int idx, String id, String nickname, String passwd, Boolean pg, String photo,
                List<Favorite> favorites, MyStudio myStudio, String role) {
        this.idx = idx;
        this.id = id;
        this.nickname = nickname;
        this.passwd = passwd;
        this.pg = pg;
        this.photo = photo;
        this.favorites = favorites;
        this.myStudio = myStudio;
        this.role=role;
    }

    // 수정할 정보 : passwd, nickname(중복체크해야됨), pg, location, introduce
    public void updateUserProfile(UserRegisterPostReq updateInfo){
        this.nickname=updateInfo.getNickname();
        this.passwd=updateInfo.getPasswd();
        this.photo=updateInfo.getPhoto();
    }

    public void upgradePhotographer(){
        this.pg=true;
        this.role="PG";
    }

    // JWT-Spring Security Settings
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String auth : role.split(",")) {
            roles.add(new SimpleGrantedAuthority(auth));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
