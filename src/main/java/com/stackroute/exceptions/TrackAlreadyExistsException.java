package com.stackroute.exceptions;

public class TrackAlreadyExistsException  extends Exception {
    String message;

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
