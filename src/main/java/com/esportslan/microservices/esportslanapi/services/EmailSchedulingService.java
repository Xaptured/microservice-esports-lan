package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.EmailClientHelper;
import com.esportslan.microservices.esportslanapi.clienthelpers.TheJackFolioDBClientHelper;
import com.esportslan.microservices.esportslanapi.models.AudienceTicket;
import com.esportslan.microservices.esportslanapi.models.EmailDetails;
import com.esportslan.microservices.esportslanapi.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSchedulingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSchedulingService.class);

    @Autowired
    private EventService eventService;
    @Autowired
    private EmailClientHelper emailClientHelper;
    @Autowired
    private TheJackFolioDBClientHelper theJackFolioDBClientHelper;

    @Scheduled(cron = "0 */15 * * * *")
    public void sendTicketsToRegisteredAudience() {
        List<AudienceTicket> audienceTickets = eventService.fetchUnsentEmailForAudience();
        if (audienceTickets != null) {
            if (audienceTickets.isEmpty()) {
                LOGGER.info("EmailScheduling: No unsent emails found for audience tickets");
            } else {
                for (AudienceTicket audienceTicket : audienceTickets) {
                    EmailDetails emailDetails = new EmailDetails();
                    String body = StringConstants.BOOKING_COMPLETE_BODY + "\n\nTicket number: " + audienceTicket.getTicketNumber() + StringConstants.THANKS;
                    emailDetails.setRecipient(audienceTicket.getEmail());
                    emailDetails.setSubject("Booking confirm for LAN event:" + audienceTicket.getEventName());
                    emailDetails.setMsgBody(body);
                    emailClientHelper.sendBookingTicketEmailToClient(emailDetails);
                    theJackFolioDBClientHelper.updateAudienceTicketStatus(audienceTicket);
                }
            }
        } else {
            LOGGER.info("EmailScheduling: No unsent emails found for audience tickets");
        }
    }
}
