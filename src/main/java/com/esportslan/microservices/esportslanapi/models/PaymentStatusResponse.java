package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.PaymentStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentStatusResponse {
    private PaymentStatus paymentStatus;
}
