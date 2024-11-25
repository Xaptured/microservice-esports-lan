package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.models.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DATABASE-SERVICE")
public interface TheJackFolioDBClient {

    @PostMapping("/events-lan/save-or-update-event")
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody Event event);
}
