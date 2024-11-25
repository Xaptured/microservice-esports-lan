package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.exceptions.FallBackException;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.servicehelpers.EventServiceHelper;
import com.esportslan.microservices.esportslanapi.services.EventService;
import com.esportslan.microservices.esportslanapi.utilities.StringConstants;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Organizer", description = "Event management APIs for organizer")
@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizerController.class);

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Save or Update Event",
            description = "Save or Update event "
    )
    @PostMapping("/save-update-event")
    @Retry(name = "save-or-update-event-retry")
    public ResponseEntity<Event> saveOrUpdateEvent(@RequestBody Event event) {
        eventService.saveOrUpdateEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}