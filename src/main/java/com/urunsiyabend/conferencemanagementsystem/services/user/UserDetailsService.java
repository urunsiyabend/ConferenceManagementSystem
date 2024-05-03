package com.urunsiyabend.conferencemanagementsystem.services.user;

import com.urunsiyabend.conferencemanagementsystem.entities.User;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    IUserRepository userRepository;
    List<User> users = (List<User>) userRepository.findAll();


    public UserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetails(user);
    }

    @RequestMapping(value = "mail")
    public User GetByMail(List<User> users, String email){
        User founduser = null;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                founduser = user;
            }
        }
        return founduser;
    }

    @RequestMapping(value = "users")
    public List<User> fetchAllUser(){
        return users;
    }
}
