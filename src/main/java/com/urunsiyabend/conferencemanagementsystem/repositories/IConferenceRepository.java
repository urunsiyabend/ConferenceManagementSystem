package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Paper;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.services.conference.InvalidSessionException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IConferenceRepository {
    Conference createConference(Conference conference);
    void updateConference(Conference conference);
    void deleteConference(Conference conference);

    Optional<Conference> findConferenceById(int id);

    Collection<Conference> findAll();

    void createSession(int conferenceId, Session session) throws InvalidSessionException;

    void updateSession(int conferenceId, Session session);

    void deleteSession(int conferenceId, Session session);

    Optional<Session> findSessionById(int conferenceId, int sessionId);

    Collection<Session> findSessionsByConferenceId(int id);

    Collection<Paper> findPapersByConferenceId(int id);

    void addPaper(int conferenceId, Paper paper);

    void updatePaper(int conferenceId, Paper paper);

    void deletePaper(int conferenceId, Paper paper);

    Paper getPaper(int conferenceId, int paperId);

    List<Integer> findReviewersIdsByPaperId(int conferenceId, int paperId);
}
