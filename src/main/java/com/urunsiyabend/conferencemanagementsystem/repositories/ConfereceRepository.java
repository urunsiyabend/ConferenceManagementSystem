package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ConfereceRepository implements IConferenceRepository {
    HashMap<Integer, Conference> conferences = new HashMap<>();

    @Autowired
    public ConfereceRepository(SessionRepository sessionRepository) {
        Conference conference = Conference.builder()
                .id(1)
                .title("Konu")
                .description("Konular")
                .sessionList(sessionRepository.findAll())
                .build();

        conferences.put(conference.getId(), conference);
    }



    @Override
    public void create(Conference conference) {

    }

    @Override
    public void update(Conference conference) {

    }

    @Override
    public void delete(Conference conference) {

    }

    public Optional<Conference> findConferenceById(int id) {
        return Optional.ofNullable(conferences.get(id));
    }

    @Override
    public Collection<Conference> findAll() {
        return List.of();
    }
}
