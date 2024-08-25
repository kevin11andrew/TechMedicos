package com.example.exception;

public class UserNotFoundException extends Exception {

    // Constructor that accepts a custom error message
    public UserNotFoundException(String message) {
        super(message);
    }
}