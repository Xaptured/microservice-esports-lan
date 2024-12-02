package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "DATABASE-SERVICE")
public interface TheJackFolioDBClient {

    @PostMapping("/events-lan/save-or-update-event")
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody Event event, @RequestParam boolean isUpdate);

    @GetMapping("/events-lan/future-events/{email}")
    public ResponseEntity<List<Event>> fetchFutureEventsWRTEmail(@PathVariable String email);

    @GetMapping("/events-lan/past-events/{email}")
    public ResponseEntity<List<Event>> fetchPastEventsWRTEmail(@PathVariable String email);

    @PostMapping("/events-lan/save-teams")
    public ResponseEntity<Void> saveTeams(@RequestBody List<LANTeam> team);

    @GetMapping("/events-lan/pending-teams/{email}")
    public ResponseEntity<List<LANTeam>> fetchTeamWithTeamMate(@PathVariable String email);

    @PostMapping("/events-lan/update-team-status")
    public ResponseEntity<Void> updateTeamStatus(@RequestParam String email, @RequestParam String eventName, @RequestParam LANTeamStatus status);

    @GetMapping("/events-lan/participant-past-events/{email}")
    public ResponseEntity<List<Event>> fetchPastEventsForParticipants(@PathVariable String email);

    @GetMapping("/events-lan/participant-future-events/{email}")
    public ResponseEntity<List<Event>> fetchFutureEventsForParticipants(@PathVariable String email);

    @PostMapping("/events-lan/save-or-update-audience")
    public ResponseEntity<Void> saveOrUpdateAudience(@RequestBody Audience audience);

    @GetMapping("/events-lan/audience-past-events/{email}")
    public ResponseEntity<List<Event>> fetchPastEventsForAudience(@PathVariable String email);

    @GetMapping("/events-lan/audience-future-events/{email}")
    public ResponseEntity<List<Event>> fetchFutureEventsForAudience(@PathVariable String email);

    @GetMapping("/events-lan/audience-live-events/{email}")
    public ResponseEntity<List<Event>> fetchLiveEventsForAudience(@PathVariable String email);

    @GetMapping("/events-lan/admin-inactive-events")
    public ResponseEntity<List<Event>> fetchInactiveEventForAdmin();

    @PostMapping("/events-lan/update-event-status")
    public ResponseEntity<Void> updateEventStatus(@RequestParam String eventName, @RequestParam EventStatus status);

    @GetMapping("/events-lan/fetch-event-details/{eventName}")
    public ResponseEntity<Event> fetchLANEventDetails(@PathVariable String eventName);

    @GetMapping("/events-lan/fetch-team-details/{eventName}")
    public ResponseEntity<List<LANTeam>> fetchParticipatedTeamDetails(@PathVariable String eventName);
}
