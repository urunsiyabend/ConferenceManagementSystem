package com.urunsiyabend.conferencemanagementsystem.dtos;

import com.urunsiyabend.conferencemanagementsystem.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {
    private int conferenceId;
    private int paperId;
    private int reviewerId;
    private int reviewId;
    private Review.ReviewStatus status;
    private String assignmentDate;
    private String dueDate;
}
