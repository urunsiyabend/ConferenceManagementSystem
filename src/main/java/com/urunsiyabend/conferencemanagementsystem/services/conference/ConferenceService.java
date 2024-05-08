package com.urunsiyabend.conferencemanagementsystem.services.conference;

import com.urunsiyabend.conferencemanagementsystem.dtos.SessionDTO;
import com.urunsiyabend.conferencemanagementsystem.entities.Conference;
import com.urunsiyabend.conferencemanagementsystem.entities.Session;
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

    public void createSession(int conferenceId,SessionDTO sessionDTO) throws InvalidSessionException {
        conferenceRepository.createSession(conferenceId,
                Session
                        .builder()
                        .id(sessionDTO.getSessionId())
                        .startDate(sessionDTO.getStartDate())
                        .endDate(sessionDTO.getEndDate())
                        .build()
        );
    }

    public Session getSessionById(int conferenceId, int Sessionid) throws ConferenceNotFoundException, SessionNotFoundServiceException {
        if (conferenceRepository.findConferenceById(conferenceId).isEmpty()) {
            throw new ConferenceNotFoundException();
        }
        return conferenceRepository.findSessionById(conferenceId, Sessionid).orElseThrow(SessionNotFoundServiceException::new);
    }

    public Collection<Session> fetchAllSessions(int conferenceId) {
        return conferenceRepository.findSessionsByConferenceId(conferenceId);
    }
}
