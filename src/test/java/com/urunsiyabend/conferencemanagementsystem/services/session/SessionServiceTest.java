package com.urunsiyabend.conferencemanagementsystem.services.session;

import com.urunsiyabend.conferencemanagementsystem.dtos.SessionDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.SessionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {
    SessionService sessionService;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService(new SessionRepository());

        sessionService.createSession(
                SessionDTO
                        .builder()
                        .sessionId(1)
                        .date(new Date(System.currentTimeMillis()))
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSessionById() throws SessionNotFoundServiceException {
        assertDoesNotThrow(() -> sessionService.getSessionById(1));
        Session session = sessionService.getSessionById(1);

        assertNotNull(session);

        assertEquals(session.getId(), 1);
    }

    @Test
    void fetchAllSessions() {
        Collection<Session> sessions = sessionService.fetchAllSessions();

        assertNotNull(sessions);

        assertFalse(sessions.isEmpty());
        assertEquals(sessions.size(), 1);
    }

    @Test
    void createSession() {
        assertThrows(SessionNotFoundServiceException.class, () -> {
            sessionService.getSessionById(2);
        });

        sessionService.createSession(
                SessionDTO
                        .builder()
                        .sessionId(2)
                        .date(new Date(System.currentTimeMillis()))
                        .build()
        );

        assertDoesNotThrow(() -> sessionService.getSessionById(2));
    }
}