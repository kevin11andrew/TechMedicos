package com.example.exception;

public class AppointmentDoesNotExistException extends Exception{
    public AppointmentDoesNotExistException(String message){
        super(message);
    }
}
