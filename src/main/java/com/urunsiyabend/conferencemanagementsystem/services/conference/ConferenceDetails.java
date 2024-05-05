package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.ConfereceRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

public class ConferenceDetails {
    private int id;
    private String title;
    private String description;
    private List<Session> sessions;
    private List<Paper> papers;

    public ConferenceDetails(Conference conference) {
        this.id = conference.getId();
        this.title = conference.getTitle();
        this.description = conference.getDescription();
        this.sessions = new ArrayList<>();
        this.papers = new List<Paper>();

    }

    public int getId() {return this.id;}

    public String getTitle() {return this.title;}

    public String getDescription() {return this.description;}

    public List<Session> getSessions() {return this.sessions;}
}
