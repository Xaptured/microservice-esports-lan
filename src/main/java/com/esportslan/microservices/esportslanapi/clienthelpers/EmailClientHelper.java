package com.esportslan.microservices.esportslanapi.clienthelpers;

import com.esportslan.microservices.esportslanapi.clients.EmailClient;
import com.esportslan.microservices.esportslanapi.exceptions.BadRequestErrorException;
import com.esportslan.microservices.esportslanapi.exceptions.InternalErrorException;
import com.esportslan.microservices.esportslanapi.models.AudienceTicket;
import com.esportslan.microservices.esportslanapi.models.EmailDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailClientHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailClientHelper.class);

    @Autowired
    private EmailClient client;

    public void sendBookingTicketEmailToClient(EmailDetails details) {
        try {
            LOGGER.info("Calling email client to send booking details to audience");
            client.sendBookingTicketEmailToClient(details);
        }  catch (InternalErrorException exception) {
            LOGGER.error("Got exception while sending booking details to audience");
            throw new InternalErrorException("Got exception while sending booking details to audience: " + exception.getMessage(), exception);
        }
    }
}
