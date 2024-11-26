package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event", description = "Event management APIs")
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

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
}
