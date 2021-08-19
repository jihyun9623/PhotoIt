package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class AuthorLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorlocation_idx")
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "location_name")
    private Location location;

    @Builder
    public AuthorLocation(int idx, MyStudio myStudio, Location location) {
        this.idx = idx;
        this.myStudio = myStudio;
        this.location = location;
    }

//    public void updateAuthorLocationProfile(MyStudio myStudio, Location location){
//        this.myStudio=myStudio;
//        this.location=location;
//    }

}
