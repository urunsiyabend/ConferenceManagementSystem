package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ConfereceRepository implements IConferenceRepository {
    HashMap<Integer, Conference> conferences = new HashMap<>();

    public ConfereceRepository(){
        Conference conference = Conference.builder()
                .id(1)
                .title("Konu")
                .description("Konular")
                .sessionList(SessionRepository.findById(1))
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

    public Optional<Conference> findById(int id) {
        return Optional.ofNullable(conferences.get(id));
    }

    @Override
    public Collection<Conference> findAll() {
        return List.of();
    }
}
