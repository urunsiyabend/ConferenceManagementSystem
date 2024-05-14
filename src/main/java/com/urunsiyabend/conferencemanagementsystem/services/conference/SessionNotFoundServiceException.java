package com.urunsiyabend.conferencemanagementsystem.services.conference;

public class SessionNotFoundServiceException extends Exception {
    public SessionNotFoundServiceException() {

    }

    public SessionNotFoundServiceException(String message) {
        super(message);
    }

    public SessionNotFoundServiceException(Throwable cause) {
        super(cause);
    }

    public SessionNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
