package com.urunsiyabend.conferencemanagementsystem.services.auth;

import com.urunsiyabend.conferencemanagementsystem.dtos.AuthenticationResponseDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterRequestDTO;
import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterResponseDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.User;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
import com.urunsiyabend.conferencemanagementsystem.services.user.UserDetails;
import com.urunsiyabend.conferencemanagementsystem.util.auth.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    // Access token expiration time in milliseconds (24 hours)
    private final long accessTokenExpiration = 24 * 60 * 60 * 1000;

    // Refresh token expiration time in milliseconds (7 days)
    private final long refreshTokenExpiration = 7 * 24 * 60 * 60 * 1000;

    @Autowired
    public AuthenticationService(
            IUserRepository repository,
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil
    ) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponseDTO authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        User user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var accessToken = jwtUtil.generateToken(new UserDetails(user), accessTokenExpiration);
        var refreshToken = jwtUtil.generateToken(new UserDetails(user), refreshTokenExpiration);

        return AuthenticationResponseDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        User user = User
                .builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getFirstName())
                .surname(request.getLastName())
                .role(User.Role.REVIEWER)
                .build();

        repository.save(user);

        var accessToken = jwtUtil.generateToken(new UserDetails(user), accessTokenExpiration);
        var refreshToken = jwtUtil.generateToken(new UserDetails(user), refreshTokenExpiration);

        return RegisterResponseDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String refreshToken(String refreshToken) {
        // TODO: Implement refresh token
        return null;
    }

    @Override
    public void logout(String accessToken) {
        // TODO: Implement logout
    }
}
