package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Participant", description = "Event management APIs for participant")
@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private EventService eventService;
    @Operation(
            summary = "Fetch pending teams with respect to email",
            description = "Fetch pending teams with respect to email"
    )
    @GetMapping("/fetch-pending-teams/{email}")
    @Retry(name = "fetch-pending teams-retry")
    public ResponseEntity<List<LANTeam>> fetchTeamWithTeamMate(@PathVariable String email) {
        List<LANTeam> events = eventService.fetchTeamWithTeamMate(email);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
}
