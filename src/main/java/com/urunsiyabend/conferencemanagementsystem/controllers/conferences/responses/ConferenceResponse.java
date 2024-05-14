package com.urunsiyabend.conferencemanagementsystem.controllers.conferences.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConferenceResponse {
    private int id;
    private String title;
    private String description;
}
