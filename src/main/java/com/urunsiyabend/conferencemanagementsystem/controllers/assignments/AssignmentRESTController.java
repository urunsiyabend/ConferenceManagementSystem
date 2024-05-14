package com.urunsiyabend.conferencemanagementsystem.controllers.assignments;

import com.urunsiyabend.conferencemanagementsystem.controllers.assignments.responses.AssignmentResponse;
import com.urunsiyabend.conferencemanagementsystem.services.conference.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/assignments")
@CrossOrigin()
public class AssignmentRESTController {
    private final ConferenceService conferenceService;

    @Autowired
    public AssignmentRESTController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments(@RequestParam int userId) {
        Collection<AssignmentResponse> assignments = conferenceService.getAssignments(userId).stream()
                .map(assignment -> AssignmentResponse.builder()
                        .id(assignment.getReviewId())
                        .conferenceId(assignment.getConferenceId())
                        .paperId(assignment.getPaperId())
                        .reviewerId(assignment.getReviewerId())
                        .assignmentDate(assignment.getAssignmentDate())
                        .dueDate(assignment.getDueDate())
                        .status(assignment.getStatus().toString())
                        .build())
                .toList();

        return ResponseEntity.ok(List.copyOf(assignments));
    }
}
