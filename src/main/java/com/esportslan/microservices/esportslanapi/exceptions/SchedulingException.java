package com.esportslan.microservices.esportslanapi.exceptions;

public class SchedulingException extends RuntimeException{

    public SchedulingException(String message) {
        super(message);
    }

    public SchedulingException(String message, Throwable cause) {
        super(message, cause);
    }
}
