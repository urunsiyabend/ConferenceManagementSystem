package com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LoginRequest {
    private final String email;
    private final String password;
}


/*
* {
*     "email": "admin@test.com",
*     "password": "admin"
* }
* */