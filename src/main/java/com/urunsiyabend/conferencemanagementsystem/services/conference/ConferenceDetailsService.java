package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.repositories.IConferenceRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class ConferenceDetailsService{
    IConferenceRepository conferenceRepository;
    List<Conference> conferences = (List<Conference>) conferenceRepository.findAll();

    public ConferenceDetailsService(IConferenceRepository conferenceRepository){
        this.conferenceRepository = conferenceRepository;
    }

    public ConferenceDetails loadConferenceById(int id) throws Exception{
        Conference conference = conferenceRepository.findById(id).orElseThrow();
        return new ConferenceDetails(conference);
    }

    @RequestMapping(value = "ConferenceId")
    public Conference GetById(List<Conference> conferences ,int id){
        Conference foundConference = null;
        for (Conference conference : conferences) {
            if (conference.getId() == id) {
                foundConference = conference;
            }
        }
        return foundConference;
    }

    public List<Conference> FetchAllConferences(){
        return conferences;
    }
}
