package com.esportslan.microservices.esportslanapi.clienthelpers;

import com.esportslan.microservices.esportslanapi.clients.TheJackFolioDBClient;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.exceptions.BadRequestErrorException;
import com.esportslan.microservices.esportslanapi.exceptions.InternalErrorException;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
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
            LOGGER.error("Got exception while getting pending teams");
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
}
