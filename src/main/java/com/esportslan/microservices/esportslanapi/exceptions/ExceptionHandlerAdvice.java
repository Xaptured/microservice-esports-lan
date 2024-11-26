package com.esportslan.microservices.esportslanapi.exceptions;

import com.esportslan.microservices.esportslanapi.models.ExceptionBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionBody> handleValidationException(ValidationException exception) {
        LOGGER.error("Exception occurred during validation: " + exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ExceptionBody> handleInternalErrorException(InternalErrorException exception) {
        LOGGER.error("Internal exception occurred: " + exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }

    @ExceptionHandler(BadRequestErrorException.class)
    public ResponseEntity<ExceptionBody> handleBadRequestErrorException(BadRequestErrorException exception) {
        LOGGER.error("Bad request exception occurred: " + exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);
    }
}
