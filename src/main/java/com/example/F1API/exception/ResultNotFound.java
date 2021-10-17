package com.example.F1API.exception;

public class ResultNotFound extends RuntimeException {

    public ResultNotFound() {
        super("Result not found");     }


    public ResultNotFound(String message) {
        super(message);
    }
}
