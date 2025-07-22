package com.cfeg.cells.models.cells.entity;

import com.cfeg.cells.models.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "cells")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String zone;

    private String address;

    @Enumerated(EnumType.STRING)
    private DayOfWeek meetingDay;

    private LocalTime meetingTime;

    private boolean archived;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private User leader;

    private String notes;

    private boolean active;
}
