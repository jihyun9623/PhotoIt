package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
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

}
