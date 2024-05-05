package com.urunsiyabend.conferencemanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.awt.print.Paper;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "conference")


public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany
    List<Session> sessionList;

    @OneToMany
    List<Paper> paperList;
}
