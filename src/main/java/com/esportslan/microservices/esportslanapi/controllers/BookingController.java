package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.StayLink;
import com.esportslan.microservices.esportslanapi.models.TravelLink;
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

@Tag(name = "Booking", description = "Booking management APIs for all type of users")
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Fetch stay links",
            description = "Fetch stay links"
    )
    @GetMapping("/events/{eventName}/stay-links")
    @Retry(name = "fetch-stay-links-retry")
    public ResponseEntity<StayLink> generateStayLink(@PathVariable String eventName) {
        StayLink stayLink = eventService.generateStayLinks(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(stayLink);
    }

    @Operation(
            summary = "Fetch travel links",
            description = "Fetch travel links"
    )
    @GetMapping("/events/{eventName}/travel-links")
    @Retry(name = "fetch-travel-links-retry")
    public ResponseEntity<TravelLink> generateTravelLink(@PathVariable String eventName) {
        TravelLink travelLink = eventService.generateTravelLinks(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(travelLink);
    }
}
