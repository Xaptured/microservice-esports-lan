package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import com.esportslan.microservices.esportslanapi.models.SubUser;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Organizer", description = "Event management APIs for organizer")
@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Save or Update Event",
            description = "Save or Update event "
    )
    @PostMapping("/save-update-event")
    @Retry(name = "save-or-update-event-retry")
    public ResponseEntity<Event> saveOrUpdateEvent(@RequestBody Event event, @RequestParam boolean isUpdate) {
        eventService.saveOrUpdateEvent(event, isUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @Operation(
            summary = "Save team and team mates",
            description = "Save team and team mates. "
    )
    @PostMapping("/save-teams")
    @Retry(name = "save-teams-retry")
    public ResponseEntity<List<LANTeam>> saveTeams(@RequestBody List<LANTeam> teams) {
        eventService.saveTeams(teams);
        return ResponseEntity.status(HttpStatus.CREATED).body(teams);
    }

    @Operation(
            summary = "Fetch future events with respect to email",
            description = "Fetch future events with respect to email"
    )
    @GetMapping("/fetch-future-events/{email}")
    @Retry(name = "fetch-future-events-retry")
    public ResponseEntity<List<Event>> fetchFutureEventsWRTEmail(@PathVariable String email) {
        List<Event> events = eventService.fetchFutureEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @Operation(
            summary = "Fetch past events with respect to email",
            description = "Fetch past events with respect to email"
    )
    @GetMapping("/fetch-past-events/{email}")
    @Retry(name = "fetch-past-events-retry")
    public ResponseEntity<List<Event>> fetchPastEventsWRTEmail(@PathVariable String email) {
        List<Event> events = eventService.fetchPastEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @Operation(
            summary = "Fetch live events with respect to email",
            description = "Fetch live events with respect to email"
    )
    @GetMapping("/fetch-live-events/{email}")
    @Retry(name = "fetch-live-events-retry")
    public ResponseEntity<List<Event>> fetchLiveEventsWRTEmail(@PathVariable String email) {
        List<Event> events = eventService.fetchLiveEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @Operation(
            summary = "Save sub user",
            description = "Save sub user. "
    )
    @PostMapping("/save-sub-user")
    @Retry(name = "save-sub-user-retry")
    public ResponseEntity<Void> saveSubUser(@RequestBody SubUser subUser) {
        eventService.saveSubUser(subUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Update sub user",
            description = "Update sub user. "
    )
    @PostMapping("/update-sub-user")
    @Retry(name = "update-sub-user-retry")
    public ResponseEntity<Void> updateSubUser(@RequestBody SubUser subUser) {
        eventService.updateSubUser(subUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Update sub user's active",
            description = "Update sub user's active. "
    )
    @PostMapping("/update-sub-user-active/{eventName}")
    @Retry(name = "update-sub-user-active-retry")
    public ResponseEntity<Void> updateSubUserActive(@PathVariable String eventName) {
        eventService.updateActive(eventName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
