package com.urunsiyabend.conferencemanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.text.DateFormat;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "session")


public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date date;

}
