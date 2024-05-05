package com.urunsiyabend.conferencemanagementsystem.services.session;

import com.urunsiyabend.conferencemanagementsystem.dtos.SessionDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import com.urunsiyabend.conferencemanagementsystem.repositories.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SessionService {
    ISessionRepository sessionRepository;

    @Autowired
    public SessionService(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createSession(SessionDTO sessionDTO) {
        sessionRepository.create(
                Session
                        .builder()
                        .id(sessionDTO.getSessionId())
                        .date(sessionDTO.getDate())
                        .build()
        );
    }

    public Session getSessionById(int id) throws SessionNotFoundServiceException {
        return sessionRepository.findSessionById(id).orElseThrow(SessionNotFoundServiceException::new);
    }

    public Collection<Session> fetchAllSessions() {
        return sessionRepository.findAll();
    }
}
