package com.esportslan.microservices.esportslanapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentImageDBRequest {
    private String tournamentName;
    private List<Image> images;
}
