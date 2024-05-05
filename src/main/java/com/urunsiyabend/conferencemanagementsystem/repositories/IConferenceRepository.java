package com.urunsiyabend.conferencemanagementsystem.repositories;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;

import java.util.Collection;
import java.util.Optional;

public interface IConferenceRepository {
    void create(Conference conference);
    void update(Conference conference);
    void delete(Conference conference);
    Optional<Conference> findById(Long id);
    Collection<Conference> findAll();

}
