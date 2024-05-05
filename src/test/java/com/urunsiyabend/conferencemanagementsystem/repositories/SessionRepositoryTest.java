package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SessionRepositoryTest {
    SessionRepository sessionRepository;
    @BeforeEach
    void setUp() {
        sessionRepository = new SessionRepository();
        Session session1 = new Session(1, new Date(System.currentTimeMillis()));
        sessionRepository.create(session1);
    }

    @Test
    void getSession() {
        Optional<Session> optSession1 = sessionRepository.findSessionById(1);
        assertTrue(optSession1.isPresent());
        Session session = optSession1.get();
        assertInstanceOf(Session.class, session);
        assertEquals(session.getId(), 1);
        assertTrue(session.isExpired(new Date(System.currentTimeMillis())));
    }
}