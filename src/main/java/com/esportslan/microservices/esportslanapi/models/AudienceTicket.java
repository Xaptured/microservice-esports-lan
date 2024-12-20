/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AudienceTicket {
    private String email;
    private String eventName;
    private String ticketNumber;
    private boolean emailSent;
}
