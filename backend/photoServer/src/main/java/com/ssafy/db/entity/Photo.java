package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Photo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_idx")
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;

    private int viewCnt;

    @NotNull
    private String origin;      //원본사진 UUID

    @NotNull
    private String thumbnail;   //썸네일 UUID

    @Column(columnDefinition = "boolean default false")   //기본값 false
    private boolean best;

    @NotNull
    private LocalDateTime upload;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<PhotoTag> photoTags = new ArrayList<>();

    @Builder
    public Photo(int idx, MyStudio myStudio,int viewCnt, String origin, String thumbnail, boolean best, LocalDateTime upload) {
        this.idx = idx;
        this.myStudio = myStudio;
        this.viewCnt = viewCnt;
        this.origin = origin;
        this.thumbnail = thumbnail;
        this.best = best;
        this.upload = upload;
    }
}
