package com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResponse {
    private int id;
    private String title;
    private String startDate;
    private String endDate;
}
