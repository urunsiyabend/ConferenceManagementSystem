package com.urunsiyabend.conferencemanagementsystem.controllers.conferences;

//import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.requests.AssignmentRequest;

import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.requests.ConferenceCreateRequest;
import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.requests.NewReviewRequest;
import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses.ConferenceResponse;
import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses.PaperResponse;
import com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses.SessionResponse;
import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Paper;
import com.urunsiyabend.conferencemanagementsystem.entities.Review;
import com.urunsiyabend.conferencemanagementsystem.services.conference.ConferenceNotFoundException;
import com.urunsiyabend.conferencemanagementsystem.services.conference.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/conferences")
@CrossOrigin()
public class ConferenceRESTController {
    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceRESTController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ConferenceResponse>> getAllConferences() {
        Collection<ConferenceResponse> conferences = conferenceService.FetchAllConferences().stream()
                .map(conference -> ConferenceResponse.builder()
                        .id(conference.getId())
                        .title(conference.getTitle())
                        .description(conference.getDescription())
                        .build())
                .toList();

        return ResponseEntity.ok(List.copyOf(conferences));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceResponse> getConferenceById(@PathVariable int id) throws ConferenceNotFoundException {
        Conference conference = conferenceService.getConferenceById(id);

        return ResponseEntity.ok(ConferenceResponse.builder()
                .id(conference.getId())
                .title(conference.getTitle())
                .description(conference.getDescription())
                .build());
    }

    @PostMapping("/")
    public ResponseEntity<ConferenceResponse> createConference(@RequestBody ConferenceCreateRequest conferenceRequest) {
        Conference conference = conferenceService.createConference(Conference.builder()
                .title(conferenceRequest.getTitle())
                .description(conferenceRequest.getDescription())
                .papers(new HashMap<>())
                        .sessions(new HashMap<>())
                .build());

        return ResponseEntity.ok(ConferenceResponse.builder()
                .id(conference.getId())
                .title(conference.getTitle())
                .description(conference.getDescription())
                .build());
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<List<SessionResponse>> getConferenceSessions(@PathVariable int id) {
        Collection<SessionResponse> sessions = conferenceService.fetchAllSessions(id).stream()
                .map(session -> SessionResponse.builder()
                        .id(session.getId())
                        .title(session.getTitle())
                        .startDate(session.getStartDate().toString())
                        .endDate(session.getEndDate().toString())
                        .build())
                .toList();

        return ResponseEntity.ok(List.copyOf(sessions));
    }

    @GetMapping("/{id}/sessions/{sessionId}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable int id, @PathVariable int sessionId) {
        SessionResponse session = SessionResponse.builder()
                .id(sessionId)
                .title("Session Title")
                .startDate("2024-05-15T12:30:00")
                .endDate("2024-05-15T13:30:00")
                .build();

        return ResponseEntity.ok(session);
    }

    @GetMapping("/{id}/papers")
    public ResponseEntity<List<PaperResponse>> getSessionPapers(@PathVariable int id) {
        Collection<PaperResponse> papers = conferenceService.fetchAllPapers(id).stream()
                .map(paper -> PaperResponse.builder()
                        .id(paper.getId())
                        .title(paper.getTitle())
                        .description(paper.getDescription())
                        .subject(paper.getSubject())
                        .reviews(paper.getReviews().stream()
                                .map(review -> PaperResponse.ReviewResponse.builder()
                                        .id(review.getId())
                                        .status(review.getStatus().toString())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return ResponseEntity.ok(List.copyOf(papers));
    }

    @PostMapping("/{id}/papers/")
    public ResponseEntity<PaperResponse> createPaper(@PathVariable int id, @RequestBody PaperResponse paperRequest) {
        Random random = new Random();
        Paper paper = Paper.builder()
                .id(random.nextInt(1000))
                .title(paperRequest.getTitle())
                .description(paperRequest.getDescription())
                .subject(paperRequest.getSubject())
                .reviews(new ArrayList<>())
                .build();

        conferenceService.addPaper(id, paper);

        paper = conferenceService.getPaper(id, paper.getId());

        return ResponseEntity.ok(PaperResponse.builder()
                .id(paper.getId())
                .title(paper.getTitle())
                .description(paper.getDescription())
                .subject(paper.getSubject())
                .reviews(paper.getReviews().stream()
                        .map(review -> PaperResponse.ReviewResponse.builder()
                                .id(review.getId())
                                .status(review.getStatus().toString())
                                .build())
                        .toList())
                .build());
    }

    @GetMapping("/{id}/papers/{paperId}")
    public ResponseEntity<PaperResponse> getPaperById(@PathVariable int id, @PathVariable int paperId) {
        Paper paper = conferenceService.getPaper(id, paperId);

        return ResponseEntity.ok(PaperResponse.builder()
                .id(paper.getId())
                .title(paper.getTitle())
                .description(paper.getDescription())
                .subject(paper.getSubject())
                .reviews(paper.getReviews().stream()
                        .map(review -> PaperResponse.ReviewResponse.builder()
                                .id(review.getId())
                                .status(review.getStatus().toString())
                                .build())
                        .toList())
                .build());
    }

    @PostMapping("/{id}/papers/{paperId}/reviews")
    public ResponseEntity<String> reviewPaper(@PathVariable int id, @PathVariable int paperId, @RequestBody NewReviewRequest reviewRequest) {
        Paper paper = conferenceService.getPaper(id, paperId);

        conferenceService.updateReview(
                id,
                paperId,
                0,
                Review.ReviewStatus.valueOf(reviewRequest.getStatus())
        );

        return ResponseEntity.ok("Review updated");
    }

    @PostMapping("/{id}/papers/{paperId}/assign")
    public ResponseEntity<String> assignPaper(
            @PathVariable int id,
            @PathVariable int paperId
//            @RequestBody AssignmentRequest assignmentRequest
    ) {
        Paper paper = conferenceService.getPaper(id, paperId);

        Date dueDate;
//        if (assignmentRequest.getDueDate() == null) {
        dueDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
//        }
//        else {
//            // convert due date (String) to Date object
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDueDate = LocalDate.parse(assignmentRequest.getDueDate(), formatter);
//            dueDate = Date.from(localDueDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
//        }

        conferenceService.assignPaper(id, paper, dueDate);

        return ResponseEntity.ok("Paper assigned");
    }

    @PutMapping("/{id}/papers/{paperId}/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable int id,
            @PathVariable int paperId,
            @PathVariable int reviewId,
            @RequestBody NewReviewRequest reviewRequest
    ) {
        Paper paper = conferenceService.getPaper(id, paperId);

        conferenceService.updateReview(
                id,
                paperId,
                reviewRequest.getReviewerId(),
                Review.ReviewStatus.valueOf(reviewRequest.getStatus())
        );

        return ResponseEntity.ok("Review updated");
    }
}
