package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
public class Photo {

    @Id @GeneratedValue
    @Column(name = "photo_idx")
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;

    private int viewcnt;

    private String origin;      //원본사진 UUID

    private String thumbnail;   //썸네일 UUID

    @Column(columnDefinition = "boolean default false")   //기본값 false
    private boolean best;

    @NotNull
    private LocalDateTime upload;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<PhotoTag> photoTags;
}
