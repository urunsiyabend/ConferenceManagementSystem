package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.*;
import com.urunsiyabend.conferencemanagementsystem.services.conference.InvalidSessionException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ConfereceRepository implements IConferenceRepository {
    HashMap<Integer, Conference> conferences = new HashMap<>();

    public ConfereceRepository() throws InvalidSessionException {
        Session session = Session.builder()
                .id(1)
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .build();

        Conference conference = Conference.builder()
                .id(1)
                .title("Konu")
                .description("Konular")
                .sessions(new HashMap<>())
                .papers(new HashMap<>())
                .build();

        conference.addSession(session);

        conferences.put(conference.getId(), conference);
    }

    @Override
    public void createConference(Conference conference) {
        conferences.put(conference.getId(), conference);
    }

    @Override
    public void updateConference(Conference conference) {
        if (conferences.containsKey(conference.getId())) {
            conferences.put(conference.getId(), conference);
        }

    }

    @Override
    public void deleteConference(Conference conference) {
        conferences.remove(conference.getId());
    }

    @Override
    public Optional<Conference> findConferenceById(int id) {
        return Optional.ofNullable(conferences.get(id));
    }

    @Override
    public Collection<Conference> findAll() {
        return List.of();
    }

    @Override
    public void createSession(int conferenceId, Session session) throws InvalidSessionException {
        conferences.get(conferenceId).addSession(session);
    }

    @Override
    public void updateSession(int conferenceId, Session session) {
        if(conferences.containsKey(conferenceId)) {
            conferences.get(conferenceId).getSession(session.getId()).setStartDate(session.getStartDate());
        }
    }

    @Override
    public void deleteSession(int conferenceId, Session session) {
        conferences.get(conferenceId).deleteSession(session.getId());
    }

    @Override
    public Optional<Session> findSessionById(int conferenceId, int sessionId) {
        return Optional.ofNullable(conferences.get(conferenceId).getSession(sessionId));
    }

    @Override
    public Collection<Session> findSessionsByConferenceId(int id) {
        Conference conference = conferences.get(id);
        return conference.getSessions().values();
    }

    @Override
    public void addPaper(int conferenceId, Paper paper) {
        conferences.get(conferenceId).addPaper(paper);
    }

    @Override
    public void updatePaper(int conferenceId, Paper paper) {
        if(conferences.containsKey(conferenceId)) {
            conferences.get(conferenceId).getPapers().remove(paper);
            conferences.get(conferenceId).addPaper(paper);
        }
    }

    @Override
    public void deletePaper(int conferenceId, Paper paper) {
        conferences.get(conferenceId).getPapers().remove(paper);
    }

    @Override
    public Paper getPaper(int conferenceId, int paperId) {
        return conferences.get(conferenceId).getPapers().get(paperId);
    }

    @Override
    public List<Integer> findReviewersIdsByPaperId(int conferenceId, int paperId) {
        Paper paper = getPaper(conferenceId , paperId);

        List<Integer> reviewerIds = new ArrayList<>();

        for (Review review : paper.getReviews()){
            reviewerIds.add(review.getReviwerId());
        }

        return reviewerIds;
    }
}
