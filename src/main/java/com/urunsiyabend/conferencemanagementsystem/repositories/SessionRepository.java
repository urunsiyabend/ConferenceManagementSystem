package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SessionRepository implements ISessionRepository {
    HashMap<Integer , Session> sessions = new HashMap<>();

    public SessionRepository() {
        Session session = Session.builder()
                .id(1)
                .date(null)
                .build();

        sessions.put(1, session);
    }

    public void create(Session session) {sessions.put(session.getId(), session);}

    public void update(Session session) {
        if(sessions.containsKey(session.getId())) {
            sessions.put(session.getId(), session);
        }
    }

    @Override
    public void delete(Session session) {

    }
    public Optional<Session> findById(int id) {
        return Optional.ofNullable(sessions.get(id));
    }

    public void delete(Integer id) {
        sessions.remove(id);
    }

    public Optional<Session> findById(Integer id) {
        return Optional.ofNullable(sessions.get(id));
    }

    public List<Session> findAll() {
        return List.copyOf(sessions.values());
    }
}
