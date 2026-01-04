package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TravelLink {
    private String destination;
    private String provider;
    private String url;
}
