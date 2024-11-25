package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zipCode;
}
