package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.models.EmailDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORCHESTRATE-SERVICE")
public interface EmailClient {
    @PostMapping("/email/send-booking-email")
    public ResponseEntity<Void> sendBookingTicketEmailToClient(@RequestBody EmailDetails details);
}
