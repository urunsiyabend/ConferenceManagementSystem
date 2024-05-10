package com.urunsiyabend.conferencemanagementsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    public enum ReviewStatus {
        ACCEPTED,
        REJECTED,
        PENDING
    }
    private int id;
    private int reviwerId;
    private Date assignDate;
    private Date dueDate;
    private ReviewStatus status;
}
