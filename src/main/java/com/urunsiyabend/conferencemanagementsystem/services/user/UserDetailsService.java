package com.urunsiyabend.conferencemanagementsystem.services.user;

import com.urunsiyabend.conferencemanagementsystem.entities.User;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserDetailsService {
    IUserRepository userRepository;
    List<User> users;

    public UserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        users = (List<User>) userRepository.findAll();
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new UserDetails(user);
//    }

//    public User GetByMail(String email){
//        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

    public List<User> fetchAllUser(){
        return users;
    }
}
