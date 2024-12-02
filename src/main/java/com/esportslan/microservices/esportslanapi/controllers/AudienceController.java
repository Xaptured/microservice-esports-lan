package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Audience", description = "Event management APIs for audience")
@RestController
@RequestMapping("/audience")
public class AudienceController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Save or Update audience",
            description = "Save or Update audience "
    )
    @PostMapping("/save-or-update-audience")
    @Retry(name = "save-or-update-audience-retry")
    public ResponseEntity<Audience> saveOrUpdateAudience(@RequestBody Audience audience) {
        eventService.saveOrUpdateAudience(audience);
        return ResponseEntity.status(HttpStatus.CREATED).body(audience);
    }
}
