package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.servicehelpers.EventServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
