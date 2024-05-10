package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.dtos.SessionDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.*;
import com.urunsiyabend.conferencemanagementsystem.repositories.IConferenceRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConferenceService {
    private final IUserRepository userRepository;
    IConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(IConferenceRepository conferenceRepository, IUserRepository userRepository) {
        this.conferenceRepository = conferenceRepository;
        this.userRepository = userRepository;
    }

    public void createConference(Conference conference) {
        conferenceRepository.createConference(conference);
    }

    public Conference getConferenceById(int id) throws ConferenceNotFoundException {
        return conferenceRepository.findConferenceById(id).orElseThrow(ConferenceNotFoundException::new);
    }

    public Collection<Conference> FetchAllConferences() {
        return conferenceRepository.findAll();
    }

    public void createSession(int conferenceId, Session session) throws InvalidSessionException {
        conferenceRepository.createSession(conferenceId,
                Session
                        .builder()
                        .id(session.getId())
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .build()
        );
    }

    public Session getSessionById(int conferenceId, int Sessionid) throws ConferenceNotFoundException, SessionNotFoundServiceException {
        if (conferenceRepository.findConferenceById(conferenceId).isEmpty()) {
            throw new ConferenceNotFoundException();
        }
        return conferenceRepository.findSessionById(conferenceId, Sessionid).orElseThrow(SessionNotFoundServiceException::new);
    }

    public Collection<Session> fetchAllSessions(int conferenceId) {
        return conferenceRepository.findSessionsByConferenceId(conferenceId);
    }

    public void addPaper(int conferenceId, Paper paper) {
        conferenceRepository.addPaper(conferenceId, paper);
    }

    public void updatePaper(int conferenceId, Paper paper) {
        conferenceRepository.updatePaper(conferenceId, paper);
    }

    public void deletePaper(int conferenceId, Paper paper) {
        conferenceRepository.deletePaper(conferenceId, paper);
    }

    public void assignPaper(int conferenceId, Paper paper) {
        List<User> reviewers = new ArrayList<>(userRepository.findAllByRole(User.Role.REVIEWER));

        Random random = new Random();
        int randomId = random.nextInt(reviewers.size());

        User reviewer = reviewers.get(randomId);

        conferenceRepository.addPaper(conferenceId, paper);
        reviewer.assignPaper(paper);

        paper.addReview(Review.builder()
                .status(Review.ReviewStatus.PENDING)
                .reviwerId(reviewer.getId())
                .assignDate(new Date(System.currentTimeMillis()))
                .dueDate(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)))
                .id(random.nextInt())
                .build())
        ;
    }

    public void reviewPaper(int conferenceId, int paperId, Review review) {
        Paper paper = conferenceRepository.getPaper(conferenceId, paperId);
        paper.addReview(review);
    }
}
