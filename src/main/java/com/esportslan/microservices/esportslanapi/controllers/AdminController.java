package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin", description = "Event management APIs for admin")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Fetch inactive events for admin",
            description = "Fetch inactive events for admin"
    )
    @GetMapping("/admin-inactive-events")
    @Retry(name = "admin-inactive-events-retry")
    public ResponseEntity<List<Event>> fetchInactiveEventForAdmin() {
        List<Event> events = eventService.fetchInactiveEventForAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @Operation(
            summary = "Update event status",
            description = "Update event status"
    )
    @PostMapping("/update-event-status")
    @Retry(name = "update-event-status-retry")
    public ResponseEntity<Void> updateEventStatus(@RequestParam String eventName, @RequestParam EventStatus status) {
        eventService.updateEventStatus(eventName, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
