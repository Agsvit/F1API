package com.example.F1API.exception;

public class TeamNotFound extends RuntimeException {

    public TeamNotFound() {
        super("Team not found");     }


    public TeamNotFound(String message) {
        super(message);
    }
}
