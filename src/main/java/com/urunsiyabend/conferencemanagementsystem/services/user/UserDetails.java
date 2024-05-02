package com.urunsiyabend.conferencemanagementsystem.services.user;

import com.urunsiyabend.conferencemanagementsystem.entities.User;
import com.urunsiyabend.conferencemanagementsystem.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of((GrantedAuthority) () -> user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public String getUsername() { return this.username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @RequestMapping(value = "mail")
    public User GetByMail(HashMap<String,User> users, String email){
        User founduser = null;
        for (User user : users.values()) {
           if (user.getEmail().equals(email)) {
               founduser = user;
           }
        }
        return founduser;
    }

    @RequestMapping(value = "users")
    public List<User> fetchAllUser(HashMap<String,User> users){
        return List.copyOf(users.values());
    }



}