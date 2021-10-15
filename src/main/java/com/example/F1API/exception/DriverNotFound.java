package com.example.F1API.exception;

public class DriverNotFound extends RuntimeException {

    public DriverNotFound() {
        super("Driver not found");     }


    public DriverNotFound(String message) {
        super(message);
    }
}
