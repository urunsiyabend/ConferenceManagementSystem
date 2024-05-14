package com.urunsiyabend.conferencemanagementsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Paper {
    private int id;
    private String subject;
    private String title;
    private String description;
    private List<Review> reviews;

    public void addReview(Review review) {
        // if there is any review with the same reviewer id throw an exception
        for (Review rev : reviews){
            if (rev.getReviewerId() == review.getReviewerId()){
                throw new IllegalArgumentException("Reviewer already assigned");
            }
        }

        reviews.add(review);
    }

    public Review.ReviewStatus getReviewStatus(int reviewId) {
        for (Review review : reviews) {
            if (review.getId() == reviewId) {
                return review.getStatus();
            }
        }
        return null;
    }

    public Review getAssignment(int assigneeId) {
        for (Review rev : reviews){
            if (rev.getReviewerId() == assigneeId){
                return rev;
            }
        }
        return null;
    }

    public void updateReview(int assigneeId, Review.ReviewStatus status) {
        Review assignment = getAssignment(assigneeId);

        if (assignment == null) {
            throw new IllegalArgumentException("No review found for the assignee");
        }

        if (getReviewStatus(assignment.getId()) == Review.ReviewStatus.ACCEPTED || getReviewStatus(assignment.getId()) == Review.ReviewStatus.REJECTED) {
            throw new IllegalArgumentException("Review already accepted or rejected");
        }

        assignment.setStatus(status);
    }
}
