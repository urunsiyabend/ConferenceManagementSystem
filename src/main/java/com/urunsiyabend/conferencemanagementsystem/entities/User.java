package com.urunsiyabend.conferencemanagementsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;
}
