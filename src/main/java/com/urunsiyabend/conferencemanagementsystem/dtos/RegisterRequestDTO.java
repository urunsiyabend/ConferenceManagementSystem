package com.urunsiyabend.conferencemanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
