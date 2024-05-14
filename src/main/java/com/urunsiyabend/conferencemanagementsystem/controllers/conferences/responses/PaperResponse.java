package com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PaperResponse {
    @Data
    @RequiredArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewResponse {
        private int id;
        private String status;
    }

    private int id;
    private String subject;
    private String title;
    private String description;
    private List<ReviewResponse> reviews;
}