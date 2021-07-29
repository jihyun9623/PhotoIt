package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Tag {

    @Id
    @Column(name = "tag_name")
    private String name;

    private int viewCnt;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PhotoTag> photoTags = new ArrayList<>();
}
