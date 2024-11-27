/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportslan.microservices.esportslanapi.models;

import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LANTeam {

    private String teamName;
    private String eventName;
    private LANTeamStatus status;
    private List<LANTeamMate> teamMates;
}
