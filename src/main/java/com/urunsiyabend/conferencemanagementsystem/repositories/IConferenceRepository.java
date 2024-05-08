package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.services.conference.InvalidSessionException;

import java.util.Collection;
import java.util.Optional;

public interface IConferenceRepository {
    void createConference(Conference conference);
    void updateConference(Conference conference);
    void deleteConference(Conference conference);

    Optional<Conference> findConferenceById(int id);

    Collection<Conference> findAll();

    void createSession(int conferenceId, Session session) throws InvalidSessionException;

    void updateSession(int conferenceId, Session session);

    void deleteSession(int conferenceId, Session session);

    Optional<Session> findSessionById(int conferenceId, int sessionId);

    Collection<Session> findSessionsByConferenceId(int id);
}
