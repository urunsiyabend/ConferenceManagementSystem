package com.urunsiyabend.conferencemanagementsystem.controllers.conferences.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConferenceCreateRequest {
    private String title;
    private String description;
}
