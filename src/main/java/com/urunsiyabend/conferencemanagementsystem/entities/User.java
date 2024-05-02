package com.urunsiyabend.conferencemanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String email;

    @Column(name = "Password", nullable=false)
    private String password;

    @Column(name = "Name", nullable=false)
    private String name;
    @Column(name = "Surname", nullable=false)
    private String surname;
    @Column(name = "Role", nullable=false)
    private String role;
}
