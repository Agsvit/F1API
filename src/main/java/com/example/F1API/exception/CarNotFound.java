package com.example.F1API.exception;


public class CarNotFound extends RuntimeException {

    public CarNotFound() {
        super("Car not found");     }

    public CarNotFound(String message) {
        super(message);
    }
}
