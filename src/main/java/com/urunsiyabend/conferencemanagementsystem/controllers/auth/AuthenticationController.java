package com.urunsiyabend.conferencemanagementsystem.controllers.auth;

import com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests.LoginRequest;
import com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests.LoginResponse;
import com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests.RegisterRequest;
import com.urunsiyabend.conferencemanagementsystem.controllers.auth.requests.RegisterResponse;
import com.urunsiyabend.conferencemanagementsystem.dtos.AuthenticationResponseDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterRequestDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterResponseDTO;
import com.urunsiyabend.conferencemanagementsystem.services.auth.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login to the system")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .accessToken(authenticationResponseDTO.getAccessToken())
                        .refreshToken(authenticationResponseDTO.getRefreshToken())
                        .build()
        );
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register to the system")
    public ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest) {
        RegisterResponseDTO registerResponseDTO = authenticationService.register(
                RegisterRequestDTO.builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .firstName(registerRequest.getFirstName())
                        .lastName(registerRequest.getLastName())
                        .build()
        );

        return ResponseEntity.ok(
                RegisterResponse.builder()
                        .accessToken(registerResponseDTO.getAccessToken())
                        .refreshToken(registerResponseDTO.getRefreshToken())
                        .build()
        );
    }
}




/*
* POST auth/login
* Request-Body
* {
*     "email": "email",
*     "password": "password"
* }
*
* Response-Body
* {
*     "accessToken": "jsaklnsdlaskda",
*     "refreshToken": "jsaklnsdlaskda"
* }
*
*
* POST submissions/1/revision
* Authorization: Bearer jsaklnsdlaskda
*
* Request-Body
* {
*     "status": "accepted"
* }
*
* Response-Body
* {
*     "message": "Submission revision status updated successfully"
* }
* */
