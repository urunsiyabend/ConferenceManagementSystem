package com.urunsiyabend.conferencemanagementsystem.dtos;

import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SessionDTO {
    private int sessionId;
    private Date startDate;
    private Date endDate;

}
