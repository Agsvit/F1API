package com.example.F1API.exception;

public class RaceNotFound extends RuntimeException {

    public RaceNotFound() {
        super("Race not found");     }


    public RaceNotFound(String message) {
        super(message);
    }
}

