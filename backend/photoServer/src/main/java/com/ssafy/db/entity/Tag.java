package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Tag {

    @Id
    @Column(name = "tag_name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PhotoTag> photoTags = new ArrayList<>();

    @Builder
    public Tag(String name, List<PhotoTag> photoTags) {
        this.name = name;
        this.photoTags = photoTags;
    }

}
