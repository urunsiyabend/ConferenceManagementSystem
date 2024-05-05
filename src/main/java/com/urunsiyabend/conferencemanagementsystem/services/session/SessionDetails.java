package com.urunsiyabend.conferencemanagementsystem.services.session;

import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.SessionRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


public class SessionDetails {
    private int sessionId;
    private Date date;

    public SessionDetails(Session session) {
        this.sessionId = session.getId();
        this.date = session.getDate();
    }
    public int getSessionId(){
        return sessionId;
    }
    public Date getDate(){
        return date;
    }


}
