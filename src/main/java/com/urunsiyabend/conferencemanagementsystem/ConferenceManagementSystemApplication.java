package com.urunsiyabend.conferencemanagementsystem;

import com.urunsiyabend.conferencemanagementsystem.dtos.RegisterRequestDTO;
import com.urunsiyabend.conferencemanagementsystem.services.auth.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConferenceManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
        return args -> {
            var admin = RegisterRequestDTO.builder()
                    .email("admin@test.com")
                    .password("admintest")
                    .firstName("admin")
                    .lastName("admin")
                    .build();

            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());
        };
    }
}
