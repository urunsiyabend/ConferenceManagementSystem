//package com.urunsiyabend.conferencemanagementsystem.services.user;
//
//import com.urunsiyabend.conferencemanagementsystem.entities.User;
//import java.util.List;
//
//public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
//
//    private String username;
//    private String password;
//    private List<GrantedAuthority> authorities;
//
//    public UserDetails(User user) {
//        this.username = user.getEmail();
//        this.password = user.getPassword();
//        this.authorities = List.of((GrantedAuthority) () -> user.getRole().toString());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities; }
//
//    @Override
//    public String getPassword() { return this.password; }
//
//    @Override
//    public String getUsername() { return this.username; }
//
//    @Override
//    public boolean isAccountNonExpired() { return true; }
//
//    @Override
//    public boolean isAccountNonLocked() { return true; }
//
//    @Override
//    public boolean isCredentialsNonExpired() { return true; }
//
//    @Override
//    public boolean isEnabled() { return true; }
//}