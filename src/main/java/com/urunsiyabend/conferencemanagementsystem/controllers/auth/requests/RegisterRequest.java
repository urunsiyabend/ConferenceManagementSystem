package com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
