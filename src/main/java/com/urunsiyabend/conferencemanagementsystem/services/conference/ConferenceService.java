package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.dtos.AssignmentDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.*;
import com.urunsiyabend.conferencemanagementsystem.repositories.IConferenceRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.IUserRepository;
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

    public Conference createConference(Conference conference) {
        return conferenceRepository.createConference(conference);
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

    public Collection<Paper> fetchAllPapers(int conferenceId) {
        return conferenceRepository.findPapersByConferenceId(conferenceId);
    }

    public Paper getPaper(int conferenceId, int paperId) {
        return conferenceRepository.getPaper(conferenceId, paperId);
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

    public void assignPaper(int conferenceId, Paper paper, Date dueDate) {
        List<User> reviewers = new ArrayList<>(userRepository.findAllByRole(User.Role.REVIEWER));

        Random random = new Random();
        int randomId = random.nextInt(reviewers.size());

        User reviewer = reviewers.get(randomId);

        conferenceRepository.addPaper(conferenceId, paper);
        reviewer.assignPaper(paper);

        paper.addReview(Review.builder()
                .status(Review.ReviewStatus.PENDING)
                .reviewerId(reviewer.getId())
                .assignDate(new Date(System.currentTimeMillis()))
                .dueDate(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)))
                .id(random.nextInt())
                .build())
        ;
    }

    public Collection<Paper> getAssignedPapers(int reviewerId) {
        Collection<Paper> papers = new ArrayList<>();

        for (Conference conference : conferenceRepository.findAll()) {
            for (Paper paper : conferenceRepository.findPapersByConferenceId(conference.getId())) {
                for (Review review : paper.getReviews()) {
                    if (review.getReviewerId() == reviewerId) {
                        papers.add(paper);
                    }
                }
            }
        }

        return papers;
    }

    public Collection<AssignmentDTO> getAssignments(int reviewerId) {
        Collection<AssignmentDTO> assignments = new ArrayList<>();

        for (Conference conference : conferenceRepository.findAll()) {
            for (Paper paper : conferenceRepository.findPapersByConferenceId(conference.getId())) {
                for (Review review : paper.getReviews()) {
                    if (review.getReviewerId() == reviewerId) {
                        assignments.add(AssignmentDTO.builder()
                                .conferenceId(conference.getId())
                                .paperId(paper.getId())
                                .reviewerId(reviewerId)
                                .reviewId(review.getId())
                                .status(review.getStatus())
                                .assignmentDate(review.getAssignDate().toString())
                                .dueDate(review.getDueDate().toString())
                                .build());
                    }
                }
            }
        }

        return assignments;
    }

    public void updateReview(int conferenceId, int paperId, int reviewerId, Review.ReviewStatus status) {
//        Collection<Paper> assignedPapers = getAssignedPapers(reviewerId);

        Conference conference = conferenceRepository.findConferenceById(conferenceId).orElseThrow(IllegalArgumentException::new);

        Paper paper = conferenceRepository.getPaper(conferenceId, paperId);

        System.out.println(paper.getReviews());

        paper.updateReview(reviewerId, status);
    }
}
