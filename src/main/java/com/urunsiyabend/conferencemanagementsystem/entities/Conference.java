package com.urunsiyabend.conferencemanagementsystem.entities;

import com.urunsiyabend.conferencemanagementsystem.services.conference.InvalidSessionException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    HashMap<Integer, Session> sessions;

//    HashMap<Integer, Paper> papers = new HashMap<>();

    public void addSession(Session session) throws InvalidSessionException {
        if (getOngoingSession(session.getStartDate()) != null) {
            throw new InvalidSessionException("There is already an ongoing session");
        }
        if (getOngoingSession(session.getEndDate()) != null) {
            throw new InvalidSessionException("There is already an ongoing session");
        }
        sessions.put(session.getId(), session);
    }

    public Session getOngoingSession(Date date) {
        for (Session s : sessions.values()) {
            if (s.getStatus(date) == Session.Status.ONGOING) {
                return s;
            }
        }

        return null;
    }

    public Session getSession(int id) {
        return sessions.get(id);
    }

    public void deleteSession(int id) {
        sessions.remove(id);
    }

//    public void addPaper(Paper paper) {
//        paperList.add(paper);
//    }
}
