package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.Feedback;
import com.esportslan.microservices.esportslanapi.services.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Feedback", description = "Feedback APIs")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Fetch feedback details with respect to email",
            description = "Fetch feedback details with respect to email"
    )
    @GetMapping("/fetch-feedback/{email}")
    @Retry(name = "fetch-feedback-retry")
    public ResponseEntity<Feedback> fetchFeedbackDetails(@PathVariable String email) {
        Feedback feedback = eventService.fetchFeedbackDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

    @Operation(
            summary = "Update feedback details",
            description = "Update feedback details"
    )
    @PostMapping("/update-feedback")
    @Retry(name = "update-feedback-retry")
    public ResponseEntity<Void> updateFeedback(@RequestBody Feedback feedback) {
        eventService.updateFeedback(feedback);
        return ResponseEntity.noContent().build();
    }
}
