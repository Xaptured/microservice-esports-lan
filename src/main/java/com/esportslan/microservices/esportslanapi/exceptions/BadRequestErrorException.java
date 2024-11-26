package com.esportslan.microservices.esportslanapi.exceptions;

public class BadRequestErrorException extends RuntimeException{

    public BadRequestErrorException(String message) {
        super(message);
    }

    public BadRequestErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
