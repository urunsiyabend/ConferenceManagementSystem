package com.urunsiyabend.conferencemanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    public enum Status {
        NOT_STARTED,
        ONGOING,
        FINISHED
    }

    private int id;

    private Date startDate;

    private Date endDate;

    public Status getStatus(Date sDate , Date eDate) {
        if (startDate != null && endDate != null) {
            if (startDate.before(sDate)) {
                return Status.NOT_STARTED;
            }
            if ((startDate.after(sDate) || startDate.equals(sDate)) && (sDate.equals(endDate) || sDate.before(endDate))) {
                return Status.ONGOING;
            }
            return Status.FINISHED;
            }
        return Status.NOT_STARTED;
    }
}
