package com.esportslan.microservices.esportslanapi.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
    public ValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
