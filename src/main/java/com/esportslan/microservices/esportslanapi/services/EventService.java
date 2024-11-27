package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.exceptions.ValidationException;
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

    public void saveOrUpdateEvent(Event event) {
        eventServiceHelper.validateEvent(event);
        theJackFolioDBClientHelper.saveOrUpdateEvent(event);
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
}
