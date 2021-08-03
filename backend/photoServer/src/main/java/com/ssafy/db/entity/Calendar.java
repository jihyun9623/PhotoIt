package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_idx")
    private int idx;

    @NotNull
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_idx")
    private MyStudio myStudio;

    @Builder
    public Calendar(int idx, LocalDateTime date, MyStudio myStudio) {
        this.idx = idx;
        this.date = date;
        this.myStudio = myStudio;
    }
}
