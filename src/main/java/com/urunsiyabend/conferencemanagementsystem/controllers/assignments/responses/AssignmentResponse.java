package com.urunsiyabend.conferencemanagementsystem.controllers.assignments.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponse {
    private int conferenceId;
    private int paperId;
    private int reviewerId;
    private int id;
    private String assignmentDate;
    private String dueDate;
    private String status;
}
