package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private int idx;

    @NotNull
    private LocalDateTime date;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<MyStudio> myStudios = new ArrayList<>();
}
