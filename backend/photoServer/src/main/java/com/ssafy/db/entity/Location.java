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
public class Location {

    @Id
    @Column(name = "location_name")
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<AuthorLocation> authorLocations = new ArrayList<>();

    @Builder
    public Location(String name, List<AuthorLocation> authorLocations) {
        this.name = name;
        this.authorLocations = authorLocations;
    }

    @Builder
    public Location(String name) {
        this.name = name;
    }

}
