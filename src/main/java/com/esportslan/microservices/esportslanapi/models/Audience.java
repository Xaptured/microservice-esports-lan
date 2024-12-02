/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Audience {
    private String name;
    private String email;
    private BigDecimal amount;
    private String transactionId;
    private PaymentStatus status;
    private String eventName;
}
