package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PhotoTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phototag_idx")
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_idx")
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_name")
    private Tag tag;

    @Builder
    public PhotoTag(int idx, Photo photo, Tag tag) {
        this.idx = idx;
        this.photo = photo;
        this.tag = tag;
    }

}
