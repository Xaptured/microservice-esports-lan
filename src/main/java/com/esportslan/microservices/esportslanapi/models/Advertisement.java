package com.esportslan.microservices.esportslanapi.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Advertisement {
    private String advertiserName;
    private String imagePath;
    private String targetUrl;
    private String altText;
    private boolean active;
}
