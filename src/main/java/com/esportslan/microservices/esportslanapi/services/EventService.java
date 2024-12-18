package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.enums.EventStatus;
import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.exceptions.ValidationException;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import com.esportslan.microservices.esportslanapi.servicehelpers.EventServiceHelper;
import com.esportslan.microservices.esportslanapi.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventServiceHelper eventServiceHelper;
    @Autowired
    private TheJackFolioDBClientHelper theJackFolioDBClientHelper;

    public void saveOrUpdateEvent(Event event, boolean isUpdate) {
        eventServiceHelper.validateEvent(event);
        theJackFolioDBClientHelper.saveOrUpdateEvent(event, isUpdate);
    }

    public void saveTeams(List<LANTeam> teams) {
        eventServiceHelper.validateTeams(teams);
        theJackFolioDBClientHelper.saveTeams(teams);
    }

    public List<Event> fetchFutureEventsWRTEmail(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsWRTEmail(email);
    }

    public List<Event> fetchPastEventsWRTEmail(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsWRTEmail(email);
    }

    public List<LANTeam> fetchTeamWithTeamMate(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchTeamWithTeamMate(email);
    }

    public void updateTeamStatus(String email, String eventName, LANTeamStatus status) {
        eventServiceHelper.validateTeamStatusUpdateParams(email, eventName, status);
        theJackFolioDBClientHelper.updateTeamStatus(email, eventName, status);
    }

    public List<Event> fetchPastEventsForParticipants(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsForParticipants(email);
    }

    public List<Event> fetchFutureEventsForParticipants(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsForParticipants(email);
    }

    public void saveOrUpdateAudience(Audience audience) {
        eventServiceHelper.validateAudience(audience);
        theJackFolioDBClientHelper.saveOrUpdateAudience(audience);
    }

    public List<Event> fetchPastEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchPastEventsForAudience(email);
    }

    public List<Event> fetchFutureEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchFutureEventsForAudience(email);
    }

    public List<Event> fetchLiveEventsForAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.fetchLiveEventsForAudience(email);
    }

    public List<Event> findLANEventsNotRegisteredByAudience(String email) {
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        return theJackFolioDBClientHelper.findLANEventsNotRegisteredByAudience(email);
    }

    public List<Event> fetchInactiveEventForAdmin() {
        return theJackFolioDBClientHelper.fetchInactiveEventForAdmin();
    }

    public void updateEventStatus(String eventName, EventStatus status) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(status.toString())) {
            throw new ValidationException("Status is invalid");
        }
        theJackFolioDBClientHelper.updateEventStatus(eventName, status);
    }

    public Event fetchLANEventDetails(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        return theJackFolioDBClientHelper.fetchLANEventDetails(eventName);
    }

    public List<LANTeam> fetchParticipatedTeamDetails(String eventName) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        return theJackFolioDBClientHelper.fetchParticipatedTeamDetails(eventName);
    }
}
