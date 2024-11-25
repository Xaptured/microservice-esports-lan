package com.esportslan.microservices.esportslanapi.models;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionBody {
    private HttpStatus httpStatus;
    private String message;
}
