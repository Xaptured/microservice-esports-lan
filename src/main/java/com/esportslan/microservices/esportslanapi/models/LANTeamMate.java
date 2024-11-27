/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LANTeamMate {

    private String email;
    private Boolean isEmailRegistered;
}
