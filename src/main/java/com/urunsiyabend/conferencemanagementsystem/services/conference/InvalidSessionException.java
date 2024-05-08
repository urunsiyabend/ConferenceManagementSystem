package com.urunsiyabend.conferencemanagementsystem.services.conference;

public class InvalidSessionException extends Exception {
    public InvalidSessionException() {
    }

    public InvalidSessionException(String message) {
        super(message);
    }
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }
    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
