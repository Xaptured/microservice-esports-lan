package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "DATABASE-SERVICE")
public interface TheJackFolioDBClient {

    @PostMapping("/events-lan/save-or-update-event")
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody Event event);

    @GetMapping("/events-lan/future-events/{email}")
    public ResponseEntity<List<Event>> fetchFutureEventsWRTEmail(@PathVariable String email);

    @GetMapping("/events-lan/past-events/{email}")
    public ResponseEntity<List<Event>> fetchPastEventsWRTEmail(@PathVariable String email);

    @PostMapping("/events-lan/save-teams")
    public ResponseEntity<Void> saveTeams(@RequestBody List<LANTeam> team);

    @GetMapping("/events-lan/pending-teams/{email}")
    public ResponseEntity<List<LANTeam>> fetchTeamWithTeamMate(@PathVariable String email);
}
