package com.urunsiyabend.conferencemanagementsystem.services.conference;

public class ConferenceNotFoundException extends Exception {
    public ConferenceNotFoundException() {

    }

    public ConferenceNotFoundException(String message) {
        super(message);
    }

    public ConferenceNotFoundException(String message, Throwable cause) {

    }

    public ConferenceNotFoundException(Throwable cause) {

    }
}
