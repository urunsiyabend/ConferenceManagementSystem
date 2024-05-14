package com.urunsiyabend.conferencemanagementsystem.entities;

import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Review {
    @Getter
    public enum ReviewStatus {
        ACCEPTED("accepted"),
        REJECTED("rejected"),
        PENDING("pending");

        private final String status;

        ReviewStatus(String status) {
            this.status = status;
        }

    }

    private int id;
    private int reviewerId;
    private Date assignDate;
    private Date dueDate;
    private ReviewStatus status;
}
