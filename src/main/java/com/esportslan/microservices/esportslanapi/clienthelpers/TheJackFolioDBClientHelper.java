package com.esportslan.microservices.esportslanapi.clienthelpers;

import com.esportslan.microservices.esportslanapi.clients.TheJackFolioDBClient;
import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.exceptions.BadRequestErrorException;
import com.esportslan.microservices.esportslanapi.exceptions.InternalErrorException;
import com.esportslan.microservices.esportslanapi.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheJackFolioDBClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheJackFolioDBClientHelper.class);

    @Autowired
    private TheJackFolioDBClient theJackFolioDBClient;

    public void saveOrUpdateEvent(Event event, boolean isUpdate) {
        try {
            LOGGER.info("Calling database client to save event");
            theJackFolioDBClient.saveOrUpdateEvent(event, isUpdate);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving lan event details");
            throw new InternalErrorException("Got exception while saving lan event details: " + exception.getMessage(), exception);
        }
    }

    public void saveTeams(List<LANTeam> teams) {
        try {
            LOGGER.info("Calling database client to save teams");
            theJackFolioDBClient.saveTeams(teams);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving team details");
            throw new InternalErrorException("Got exception while saving team details: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchFutureEventsWRTEmail(String email) {
        try {
            LOGGER.info("Calling database client to get list of future event for email: {}", email);
            return theJackFolioDBClient.fetchFutureEventsWRTEmail(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while getting future lan event details");
            throw new BadRequestErrorException("Got exception while getting future lan event details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while getting future lan event details");
            throw new InternalErrorException("Got exception while getting lan future event details: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchPastEventsWRTEmail(String email) {
        try {
            LOGGER.info("Calling database client to get list of past event for email: {}", email);
            return theJackFolioDBClient.fetchPastEventsWRTEmail(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while getting past lan event details");
            throw new BadRequestErrorException("Got exception while getting past lan event details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while getting past lan event details");
            throw new InternalErrorException("Got exception while getting past lan event details: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchLiveEventsWRTEmail(String email) {
        try {
            LOGGER.info("Calling database client to get list of live event for email: {}", email);
            return theJackFolioDBClient.fetchLiveEventsWRTEmail(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while getting live lan event details");
            throw new BadRequestErrorException("Got exception while getting live lan event details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while getting live lan event details");
            throw new InternalErrorException("Got exception while getting live lan event details: " + exception.getMessage(), exception);
        }
    }

    public List<LANTeam> fetchTeamWithTeamMate(String email) {
        try {
            LOGGER.info("Calling database client to get list of pending teams for email: {}", email);
            return theJackFolioDBClient.fetchTeamWithTeamMate(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while getting pending teams");
            throw new BadRequestErrorException("Got exception while getting pending teams: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while getting pending teams");
            throw new InternalErrorException("Got exception while getting pending teams: " + exception.getMessage(), exception);
        }
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        try {
            LOGGER.info("Calling database client to update team status for email: {}", email);
            theJackFolioDBClient.updateTeamStatus(email, eventName, status);
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while updating team status");
            throw new BadRequestErrorException("Got exception while updating team status: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while updating team status");
            throw new InternalErrorException("Got exception while updating team status: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchPastEventsForParticipants(String email) {
        try {
            LOGGER.info("Calling database client to fetch past events for participant with an email: {}", email);
            return theJackFolioDBClient.fetchPastEventsForParticipants(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching past events for participant");
            throw new BadRequestErrorException("Got exception while fetching past events for participant: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching past events for participant");
            throw new InternalErrorException("Got exception while fetching past events for participant: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchFutureEventsForParticipants(String email) {
        try {
            LOGGER.info("Calling database client to fetch future events for participant with an email: {}", email);
            return theJackFolioDBClient.fetchFutureEventsForParticipants(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching future events for participant");
            throw new BadRequestErrorException("Got exception while fetching future events for participant: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching future events for participant");
            throw new InternalErrorException("Got exception while fetching future events for participant: " + exception.getMessage(), exception);
        }
    }

    public void saveOrUpdateAudience(Audience audience) {
        try {
            LOGGER.info("Calling database client to save audience details with an email: {}", audience.getEmail());
            theJackFolioDBClient.saveOrUpdateAudience(audience);
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while saving audience details");
            throw new BadRequestErrorException("Got exception while saving audience details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving audience details");
            throw new InternalErrorException("Got exception while saving audience details: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchPastEventsForAudience(String email) {
        try {
            LOGGER.info("Calling database client to fetch past events for audience with an email: {}", email);
            return theJackFolioDBClient.fetchPastEventsForAudience(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching past events for audience");
            throw new BadRequestErrorException("Got exception while fetching past events for participant: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching past events for audience");
            throw new InternalErrorException("Got exception while fetching past events for participant: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchFutureEventsForAudience(String email) {
        try {
            LOGGER.info("Calling database client to fetch future events for audience with an email: {}", email);
            return theJackFolioDBClient.fetchFutureEventsForAudience(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching future events for audience");
            throw new BadRequestErrorException("Got exception while fetching future events for audience: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching future events for audience");
            throw new InternalErrorException("Got exception while fetching future events for audience: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchLiveEventsForAudience(String email) {
        try {
            LOGGER.info("Calling database client to fetch live events for audience with an email: {}", email);
            return theJackFolioDBClient.fetchLiveEventsForAudience(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching live events for audience");
            throw new BadRequestErrorException("Got exception while fetching live events for audience: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching live events for audience");
            throw new InternalErrorException("Got exception while fetching live events for audience: " + exception.getMessage(), exception);
        }
    }

    public List<Event> findLANEventsNotRegisteredByAudience(String email) {
        try {
            LOGGER.info("Calling database client to fetch unregistered events for audience with an email: {}", email);
            return theJackFolioDBClient.findLANEventsNotRegisteredByAudience(email).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching unregistered events for audience");
            throw new BadRequestErrorException("Got exception while fetching unregistered events for audience: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching unregistered events for audience");
            throw new InternalErrorException("Got exception while fetching unregistered events for audience: " + exception.getMessage(), exception);
        }
    }

    public List<Event> fetchInactiveEventForAdmin() {
        try {
            LOGGER.info("Calling database client to fetch inactive events for admin");
            return theJackFolioDBClient.fetchInactiveEventForAdmin().getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching inactive events for admin");
            throw new BadRequestErrorException("Got exception while fetching inactive events for admin: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching inactive events for admin");
            throw new InternalErrorException("Got exception while fetching inactive events for admin: " + exception.getMessage(), exception);
        }
    }

    public void updateEventStatus(String eventName, EventStatus status) {
        try {
            LOGGER.info("Calling database client to update event status for event name: {}", eventName);
            theJackFolioDBClient.updateEventStatus(eventName, status);
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while updating event status");
            throw new BadRequestErrorException("Got exception while updating event status: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while updating event status");
            throw new InternalErrorException("Got exception while updating event status: " + exception.getMessage(), exception);
        }
    }

    public Event fetchLANEventDetails(String eventName) {
        try {
            LOGGER.info("Calling database client to fetch event details for event name: {}", eventName);
            return theJackFolioDBClient.fetchLANEventDetails(eventName).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching event details");
            throw new BadRequestErrorException("Got exception while fetching event details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching event details");
            throw new InternalErrorException("Got exception while fetching event details: " + exception.getMessage(), exception);
        }
    }

    public List<LANTeam> fetchParticipatedTeamDetails(String eventName) {
        try {
            LOGGER.info("Calling database client to fetch team details for event name: {}", eventName);
            return theJackFolioDBClient.fetchParticipatedTeamDetails(eventName).getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching team details");
            throw new BadRequestErrorException("Got exception while fetching team details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching team details");
            throw new InternalErrorException("Got exception while fetching team details: " + exception.getMessage(), exception);
        }
    }

    public void saveAudienceTicket(AudienceTicket audienceTicket) {
        try {
            LOGGER.info("Calling database client to save audience ticket details with an email: {}", audienceTicket.getEmail());
            theJackFolioDBClient.saveAudienceTicket(audienceTicket);
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while saving audience ticket details");
            throw new BadRequestErrorException("Got exception while saving audience ticket details: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving audience ticket details");
            throw new InternalErrorException("Got exception while saving audience ticket details: " + exception.getMessage(), exception);
        }
    }

    public long fetchUnsentEmailForAudienceCount() {
        try {
            LOGGER.info("Calling database client to fetch count of unsent emails");
            return theJackFolioDBClient.fetchUnsentEmailForAudienceCount().getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching count of unsent emails");
            throw new BadRequestErrorException("Got exception while fetching count of unsent emails: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching count of unsent emails");
            throw new InternalErrorException("Got exception while fetching count of unsent emails: " + exception.getMessage(), exception);
        }
    }

    public List<AudienceTicket> fetchUnsentEmailForAudience() {
        try {
            LOGGER.info("Calling database client to fetch unsent emails for audience");
            return theJackFolioDBClient.fetchUnsentEmailForAudience().getBody();
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while fetching unsent emails for audience");
            throw new BadRequestErrorException("Got exception while fetching unsent emails for audience: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching unsent emails for audience");
            throw new InternalErrorException("Got exception while fetching unsent emails for audience: " + exception.getMessage(), exception);
        }
    }

    public void updateAudienceTicketStatus(AudienceTicket audienceTicket) {
        try {
            LOGGER.info("Calling database client to update booking ticket status");
            theJackFolioDBClient.updateAudienceTicketStatus(audienceTicket);
        } catch (BadRequestErrorException exception) {
            LOGGER.error("Got exception while updating booking ticket status");
            throw new BadRequestErrorException("Got exception while updating booking ticket status: " + exception.getMessage(), exception);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while updating booking ticket status");
            throw new InternalErrorException("Got exception while updating booking ticket status: " + exception.getMessage(), exception);
        }
    }

    public void savePendingPayments(Audience audience) {
        try {
            LOGGER.info("Calling database client to save audience pending payment");
            theJackFolioDBClient.savePendingPayments(audience);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving audience pending payment details");
            throw new InternalErrorException("Got exception while saving audience pending payment details: " + exception.getMessage(), exception);
        }
    }

    public void saveFailedPayments(Audience audience) {
        try {
            LOGGER.info("Calling database client to save audience failed payment");
            theJackFolioDBClient.saveFailedPayments(audience);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving audience failed payment details");
            throw new InternalErrorException("Got exception while saving audience failed payment details: " + exception.getMessage(), exception);
        }
    }

    public void saveInitiatePayment(Audience audience) {
        try {
            LOGGER.info("Calling database client to save audience initiated payment");
            theJackFolioDBClient.saveInitiatePayment(audience);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving audience initiated payment details");
            throw new InternalErrorException("Got exception while saving audience initiated payment details: " + exception.getMessage(), exception);
        }
    }

    public List<Audience> fetchPendingPayments() {
        try {
            LOGGER.info("Calling database client to fetch all audience pending payment");
            return theJackFolioDBClient.fetchPendingPayments().getBody();
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching all audience pending payment");
            throw new InternalErrorException("Got exception while fetching all audience pending payment: " + exception.getMessage(), exception);
        }
    }

    public List<Audience> fetchFailedPayments() {
        try {
            LOGGER.info("Calling database client to fetch all audience failed payment");
            return theJackFolioDBClient.fetchFailedPayments().getBody();
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching all audience failed payment");
            throw new InternalErrorException("Got exception while fetching all audience failed payment: " + exception.getMessage(), exception);
        }
    }

    public Audience fetchInitiatePayment(String merchantTransactionId) {
        try {
            LOGGER.info("Calling database client to fetch audience initiated payment");
            return theJackFolioDBClient.fetchInitiatePayment(merchantTransactionId).getBody();
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching audience initiated payment");
            throw new InternalErrorException("Got exception while fetching audience initiated payment: " + exception.getMessage(), exception);
        }
    }

    public void deletePendingPayment(String email, String eventName) {
        try {
            LOGGER.info("Calling database client to delete pending payment");
            theJackFolioDBClient.deletePendingPayment(email, eventName);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while deleting pending payment");
            throw new InternalErrorException("Got exception while deleting pending payment: " + exception.getMessage(), exception);
        }
    }

    public void deleteFailedPayment(String email, String eventName) {
        try {
            LOGGER.info("Calling database client to delete failed payment");
            theJackFolioDBClient.deleteFailedPayment(email, eventName);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while deleting failed payment");
            throw new InternalErrorException("Got exception while deleting failed payment: " + exception.getMessage(), exception);
        }
    }

    public void saveSubUser(SubUser subUser) {
        try {
            LOGGER.info("Calling database client to save sub user");
            theJackFolioDBClient.saveSubUser(subUser);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while saving sub user");
            throw new InternalErrorException("Got exception while saving sub user: " + exception.getMessage(), exception);
        }
    }

    public void updateSubUser(SubUser subUser) {
        try {
            LOGGER.info("Calling database client to update sub user");
            theJackFolioDBClient.updateSubUser(subUser);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while updating sub user");
            throw new InternalErrorException("Got exception while updating sub user: " + exception.getMessage(), exception);
        }
    }

    public void updateActive(String eventName) {
        try {
            LOGGER.info("Calling database client to update active for sub users");
            theJackFolioDBClient.updateActive(eventName);
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while updating active for sub users");
            throw new InternalErrorException("Got exception while updating active for sub users: " + exception.getMessage(), exception);
        }
    }

    public List<SubUser> fetchUnsentEmailSubUsers() {
        try {
            LOGGER.info("Calling database client to fetch unsent emails for sub users");
            return theJackFolioDBClient.fetchUnsentEmailSubUsers().getBody();
        } catch (InternalErrorException exception) {
            LOGGER.error("Got exception while fetching unsent emails for sub users");
            throw new InternalErrorException("Got exception while fetching unsent emails for sub users: " + exception.getMessage(), exception);
        }
    }
}
