package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.UpdateCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequest {
    private UpdateCategory category;
    private String tournamentId;
    private String title;
    private String message;
}
