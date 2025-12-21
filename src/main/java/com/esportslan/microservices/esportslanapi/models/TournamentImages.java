package com.esportslan.microservices.esportslanapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentImages {
    private String tournamentName;
    private List<MultipartFile> images;
}
