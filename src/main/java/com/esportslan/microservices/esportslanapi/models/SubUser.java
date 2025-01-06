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
public class SubUser {
    private String name;
    private String email;
    private String userName;
    private String userPassword;
    private String eventName;
    private boolean isEmailSent;
    private boolean isActive;
}
