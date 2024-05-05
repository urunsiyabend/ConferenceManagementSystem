package com.urunsiyabend.conferencemanagementsystem.services.session;

import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.ISessionRepository;
import com.urunsiyabend.conferencemanagementsystem.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;


public class SessionDetailsService {
    ISessionRepository sessionRepository;
    List<Session> sessions = (List<Session>) sessionRepository.findAll();

    public SessionDetailsService(ISessionRepository sessionRepository) {this.sessionRepository = sessionRepository;}

    public SessionDetails loadBySessionId(int id) throws Exception {
        Session session =  sessionRepository.findById(id).orElse(null);
        return new SessionDetails(session);
    }


    @RequestMapping(value = "SessionId")
    public Session getSessionBySessionId(List<Session> sessions , int id) {
        Session foundSession = null;
        for (Session session : sessions) {
            if (session.getId() == id) {
                foundSession = session;
            }
        }
        return foundSession;
    }

    public List<Session> fetchAllSessions() {
        return sessions;
    }


}
