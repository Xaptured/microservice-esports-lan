package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentInitiationRequest {
    private String name;
    private String email;
    private long amount;
    private String eventName;
}
