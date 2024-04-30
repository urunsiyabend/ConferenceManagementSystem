package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface IUserRepository {
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);
    Collection<User> findAll();
}
