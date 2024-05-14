package com.urunsiyabend.conferencemanagementsystem.controllers.users;

import com.urunsiyabend.conferencemanagementsystem.controllers.users.responses.UserResponse;
import com.urunsiyabend.conferencemanagementsystem.services.user.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class UserRESTController {
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserRESTController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        Collection<UserResponse> users = userDetailsService.fetchAllUser().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .role(String.valueOf(user.getRole()))
                        .build())
                .toList();

        return ResponseEntity.ok(List.copyOf(users));
    }
}
