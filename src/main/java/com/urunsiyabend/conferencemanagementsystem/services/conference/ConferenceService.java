package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.repositories.IConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConferenceService {
    IConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(IConferenceRepository conferenceRepository){
        this.conferenceRepository = conferenceRepository;
    }

    public Conference getConferenceById(int id) throws ConferenceNotFoundException {
        return conferenceRepository.findConferenceById(id).orElseThrow(ConferenceNotFoundException::new);
    }

    public Collection<Conference> FetchAllConferences(){
        return conferenceRepository.findAll();
    }
}
