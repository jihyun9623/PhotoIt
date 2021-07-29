package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Location {

    @Id
    @Column(name = "location_name")
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<AuthorLocation> authorLocations = new ArrayList<>();


}
