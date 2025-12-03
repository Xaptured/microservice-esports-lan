package com.esportslan.microservices.esportslanapi.exceptions;

public class EventPublisherException extends RuntimeException {
    public EventPublisherException(String message) {
        super(message);
    }

    public EventPublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
