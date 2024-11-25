package com.esportslan.microservices.esportslanapi.clienthelpers;

import com.esportslan.microservices.esportslanapi.clients.TheJackFolioDBClient;
import com.esportslan.microservices.esportslanapi.exceptions.InternalErrorException;
import com.esportslan.microservices.esportslanapi.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class TheJackFolioDBClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheJackFolioDBClientHelper.class);

    @Autowired
    private TheJackFolioDBClient theJackFolioDBClient;

    public void saveOrUpdateEvent(Event event) {
        try {
            LOGGER.info("Calling database client to save event");
            theJackFolioDBClient.saveOrUpdateEvent(event);
        } catch (HttpServerErrorException exception) {
            LOGGER.error("Got exception while saving lan event details");
            throw new InternalErrorException("Got exception while saving lan event details", exception);
        }
    }
}
