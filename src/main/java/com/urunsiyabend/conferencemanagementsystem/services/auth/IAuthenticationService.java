package com.urunsiyabend.conferencemanagementsystem.services.auth;

import com.urunsiyabend.conferencemanagementsystem.dtos.AuthenticationResponseDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterRequestDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterResponseDTO;

public interface IAuthenticationService {
    AuthenticationResponseDTO authenticate(String email, String password);
    RegisterResponseDTO register(RegisterRequestDTO request);
    String refreshToken(String refreshToken);
    void logout(String accessToken);
}
