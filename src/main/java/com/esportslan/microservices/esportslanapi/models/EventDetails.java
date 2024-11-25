package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.EventType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDetails {
    private EventType eventType;
    private BigDecimal prizePool;
    private Integer totalSlots;
    private String date;
}
