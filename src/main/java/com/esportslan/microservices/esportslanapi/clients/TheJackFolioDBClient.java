package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.models.*;
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

    @GetMapping("/events-lan/live-events/{email}")
    public ResponseEntity<List<Event>> fetchLiveEventsWRTEmail(@PathVariable String email);

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

    @GetMapping("/events-lan/audience-unregistered-events/{email}")
    public ResponseEntity<List<Event>> findLANEventsNotRegisteredByAudience(@PathVariable String email);

    @GetMapping("/events-lan/admin-inactive-events")
    public ResponseEntity<List<Event>> fetchInactiveEventForAdmin();

    @PostMapping("/events-lan/update-event-status")
    public ResponseEntity<Void> updateEventStatus(@RequestParam String eventName, @RequestParam EventStatus status);

    @GetMapping("/events-lan/fetch-event-details/{eventName}")
    public ResponseEntity<Event> fetchLANEventDetails(@PathVariable String eventName);

    @GetMapping("/events-lan/fetch-team-details/{eventName}")
    public ResponseEntity<List<LANTeam>> fetchParticipatedTeamDetails(@PathVariable String eventName);

    @PostMapping("/events-lan/save-audience-ticket")
    public ResponseEntity<Void> saveAudienceTicket(@RequestBody AudienceTicket audienceTicket);

    @GetMapping("/events-lan/fetch-unsent-email-count")
    public ResponseEntity<Long> fetchUnsentEmailForAudienceCount();

    @GetMapping("/events-lan/fetch-unsent-emails")
    public ResponseEntity<List<AudienceTicket>> fetchUnsentEmailForAudience();

    @PostMapping("/events-lan/update-audience-ticket-status")
    public ResponseEntity<Void> updateAudienceTicketStatus(@RequestBody AudienceTicket audienceTicket);

    @PostMapping("/events-lan/save-pending-payments")
    public ResponseEntity<Void> savePendingPayments(@RequestBody Audience audience);

    @PostMapping("/events-lan/save-failed-payments")
    public ResponseEntity<Void> saveFailedPayments(@RequestBody Audience audience);

    @GetMapping("/events-lan/fetch-pending-payments")
    public ResponseEntity<List<Audience>> fetchPendingPayments();

    @GetMapping("/events-lan/fetch-failed-payments")
    public ResponseEntity<List<Audience>> fetchFailedPayments();

    @DeleteMapping("/events-lan/delete-pending-payment")
    public ResponseEntity<Void> deletePendingPayment(@RequestParam String email, @RequestParam String eventName);

    @DeleteMapping("/events-lan/delete-failed-payment")
    public ResponseEntity<Void> deleteFailedPayment(@RequestParam String email, @RequestParam String eventName);

    @PostMapping("/events-lan/save-initiated-payment")
    public ResponseEntity<Void> saveInitiatePayment(@RequestBody Audience audience);

    @GetMapping("/events-lan/fetch-initiate-payment")
    public ResponseEntity<Audience> fetchInitiatePayment(@RequestParam String merchantTransactionId);

    @PostMapping("/events-lan/save-sub-user")
    public ResponseEntity<Void> saveSubUser(@RequestBody SubUser subUser);

    @PostMapping("/events-lan/update-sub-user")
    public ResponseEntity<Void> updateSubUser(@RequestBody SubUser subUser);

    @PostMapping("/events-lan/update-active/{eventName}")
    public ResponseEntity<Void> updateActive(@PathVariable String eventName);

    @GetMapping("/events-lan/fetch-unsent-email-sub-users")
    public ResponseEntity<List<SubUser>> fetchUnsentEmailSubUsers();

    @GetMapping("/events-lan/fetch-sub-user-by-username/{username}")
    public ResponseEntity<SubUser> fetchSubUserByUsername(@PathVariable String username);

    @GetMapping("/events-lan/fetch-audience-ticket-details")
    public ResponseEntity<AudienceTicket> fetchAudienceTicketDetails(@RequestParam String eventName, @RequestParam String email);

    @PostMapping("/events-lan/update-audience-check-in-status")
    public ResponseEntity<Void> updateCheckedInStatus(@RequestParam String eventName, @RequestParam String email);

    @GetMapping("/events-lan/fetch-feedback-by-email/{email}")
    public ResponseEntity<Feedback> fetchFeedbackByEmail(@PathVariable String email);

    @PostMapping("/events-lan/update-feedback")
    public ResponseEntity<Void> updateFeedback(@RequestBody List<Feedback> feedbacks);

    @GetMapping("/events-lan/fetch-feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbackExactlyOneMonthOld();

    @PostMapping("/events-lan/save-advertisement")
    public ResponseEntity<Void> saveAdvertisement(@RequestBody Advertisement advertisement);

    @GetMapping("/events-lan/fetch-advertisements")
    public ResponseEntity<List<Advertisement>> getAdvertisementDetails();
}
