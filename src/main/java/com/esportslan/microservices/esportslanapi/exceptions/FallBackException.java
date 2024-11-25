package com.esportslan.microservices.esportslanapi.exceptions;

public class FallBackException extends RuntimeException{
    public FallBackException(String message) {
        super(message);
    }

    public FallBackException(String message, Throwable cause) {
        super(message, cause);
    }
}
