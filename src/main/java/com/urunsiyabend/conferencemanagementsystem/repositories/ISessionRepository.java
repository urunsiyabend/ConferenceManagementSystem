package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Session;

import java.util.Collection;
import java.util.Optional;

public interface ISessionRepository {
    void create(Session session);
    void update(Session session);
    void delete(Session session);
    Optional<Session> findSessionById(int id);
    Collection<Session> findAll();
}
