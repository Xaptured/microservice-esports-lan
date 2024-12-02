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

@Tag(name = "LAN-Event", description = "Event management APIs for all type of users")
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Fetch events details",
            description = "Fetch event details"
    )
    @GetMapping("/fetch-event-details/{eventName}")
    @Retry(name = "fetch-event-details-retry")
    public ResponseEntity<Event> fetchLANEventDetails(@PathVariable String eventName) {
        Event event = eventService.fetchLANEventDetails(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @Operation(
            summary = "Fetch team details",
            description = "Fetch team details"
    )
    @GetMapping("/fetch-team-details/{eventName}")
    @Retry(name = "fetch-team-details-retry")
    public ResponseEntity<List<LANTeam>> fetchParticipatedTeamDetails(@PathVariable String eventName) {
        List<LANTeam> teams = eventService.fetchParticipatedTeamDetails(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }
}
