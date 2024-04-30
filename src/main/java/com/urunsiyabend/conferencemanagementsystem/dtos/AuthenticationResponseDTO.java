package com.urunsiyabend.conferencemanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponseDTO {
    private String accessToken;
    private String refreshToken;
}
