package com.urunsiyabend.conferencemanagementsystem.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public void updateReview(Review review) {
        for (Review review1 : reviews){
            if (review1.getId() == review.getId()){
                reviews.remove(review1);
                reviews.add(review);
            }
        }
    }

}
