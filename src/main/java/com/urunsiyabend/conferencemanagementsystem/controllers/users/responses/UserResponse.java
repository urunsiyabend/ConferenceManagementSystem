package com.urunsiyabend.conferencemanagementsystem.controllers.users.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String email;
    private String name;
    private String surname;
    private String role;
}
