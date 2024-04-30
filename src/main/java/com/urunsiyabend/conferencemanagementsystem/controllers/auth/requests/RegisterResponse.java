package com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private final String accessToken;
    private final String refreshToken;
}
