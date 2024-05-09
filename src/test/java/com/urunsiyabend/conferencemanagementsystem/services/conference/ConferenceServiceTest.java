package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.dtos.SessionDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.ConfereceRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.IConferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceServiceTest {
    private ConferenceService conferenceService;

    @BeforeEach
    void setUp() throws Exception {
        IConferenceRepository conferenceRepository = new ConfereceRepository();
        conferenceService = new ConferenceService(conferenceRepository);
        conferenceService.createConference(new Conference(2,"title" ,"descp", new HashMap<>()));
    }

    @Test
    void getConferenceById() throws ConferenceNotFoundException {
        assertDoesNotThrow(() -> {
                conferenceService.getConferenceById(1);
                assertNotNull(conferenceService.getConferenceById(1));
        });

        assertThrows(ConferenceNotFoundException.class, () -> {
            conferenceService.getConferenceById(-1);
        });
    }

    @Test
    void createSession() throws SessionNotFoundServiceException, ConferenceNotFoundException,InvalidSessionException {
        conferenceService.createSession(1, new Session(3, new Date(2024,5,15,12,30,0) , new Date(2024, 5,15,13,30,0)));
        assertDoesNotThrow(() ->
                conferenceService.getSessionById(1,3)
        );
        Conference conference = conferenceService.getConferenceById(1);

        assertThrows(InvalidSessionException.class ,() ->
                conferenceService.createSession(1 , new Session(4 , new Date(2024, 5 , 15,12,30,0),  new Date(2024, 5 , 15,13,0,0)))

        );

        assertDoesNotThrow(() ->
                conferenceService.createSession(1 , new Session(5, new Date(2024,6,15,12,30,0),  new Date(2024, 6,15,13,0,0))));

        assertDoesNotThrow(() ->
                conferenceService.createSession(2 ,new Session(4 , new Date(2024, 5 , 15,12,30,0),  new Date(2024, 5 , 15,13,0,0))));

        Session session = conference.getSession(3);
        assertNotNull(session);

        assertEquals(session.getId(), 3);
    }

    @Test
    void getSessionById() {
        assertDoesNotThrow(() -> {
            conferenceService.getSessionById(1, 1);
        });

        assertThrows(ConferenceNotFoundException.class, () -> {
            conferenceService.getSessionById(-1, 1);
        });

        assertThrows(SessionNotFoundServiceException.class, () -> {
            conferenceService.getSessionById(1, -1);
        });
    }

    @Test
    void fetchAllSessions() {

    }
}