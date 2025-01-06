package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private String name;
    private String email;
    private String gameName;
    private Address address;
    private EventDetails eventDetails;
    private EventStatus eventStatus;
    private boolean startCheckInProcess;
}
