package com.urunsiyabend.conferencemanagementsystem.services.user;

import com.urunsiyabend.conferencemanagementsystem.entities.User;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    IUserRepository userRepository;

    public UserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetails(user);
    }
}
