package com.esportslan.microservices.esportslanapi.servicehelpers;

import com.esportslan.microservices.esportslanapi.enums.LANTeamStatus;
import com.esportslan.microservices.esportslanapi.exceptions.ValidationException;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.models.LANTeam;
import com.esportslan.microservices.esportslanapi.models.LANTeamMate;
import com.esportslan.microservices.esportslanapi.utilities.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EventServiceHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceHelper.class);

    public void validateEvent(Event event) {
        LOGGER.info("Validating event object");

        if (Utils.isStringEmptyOrBlank(event.getEmail())) {
            throw new ValidationException("Email is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getName())) {
            throw new ValidationException("Event name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getGameName())) {
            throw new ValidationException("Game name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getAddress().getAddressLineOne())) {
            throw new ValidationException("Address line one is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getAddress().getCity())) {
            throw new ValidationException("City is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getAddress().getState())) {
            throw new ValidationException("State is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getAddress().getZipCode())) {
            throw new ValidationException("Zip code is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getEventDetails().getEventType().toString())) {
            throw new ValidationException("Event type is invalid");
        }
        if (Utils.isStringEmptyOrBlank(event.getEventDetails().getDate()) && !Utils.isFutureDate(event.getEventDetails().getDate())) {
            throw new ValidationException("Date is invalid");
        }
        if (event.getEventDetails().getTotalSlots()<=0) {
            throw new ValidationException("Total slots is invalid");
        }
        if (event.getEventDetails().getPrizePool().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Prize pool is invalid");
        }
    }

    public void validateTeams(List<LANTeam> teams) {
        LOGGER.info("Validating teams list");

        for (LANTeam team : teams) {
            if (Utils.isStringEmptyOrBlank(team.getTeamName())) {
                throw new ValidationException("Team name is invalid");
            }
            if (Utils.isStringEmptyOrBlank(team.getEventName())) {
                throw new ValidationException("Event name is invalid");
            }
            if (Utils.isStringEmptyOrBlank(team.getStatus().toString())) {
                throw new ValidationException("Team status is invalid");
            }
            if (team.getTeamMates().isEmpty()) {
                throw new ValidationException("Team mate count should be greater than 0");
            }
            for (LANTeamMate teamMate : team.getTeamMates()) {
                if (Utils.isStringEmptyOrBlank(teamMate.getEmail())) {
                    throw new ValidationException("Team mate email is invalid");
                }
            }
        }
    }

    public void validateTeamStatusUpdateParams(String email, String eventName, LANTeamStatus status) {
        if (Utils.isStringEmptyOrBlank(eventName)) {
            throw new ValidationException("Event name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(email)) {
            throw new ValidationException("Email is invalid");
        }
        if (Utils.isStringEmptyOrBlank(status.toString())) {
            throw new ValidationException("Status is invalid");
        }
    }

    public void validateAudience(Audience audience) {
        LOGGER.info("Validating audience object");

        if (Utils.isStringEmptyOrBlank(audience.getEmail())) {
            throw new ValidationException("Email is invalid");
        }
        if (Utils.isStringEmptyOrBlank(audience.getName())) {
            throw new ValidationException("Name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(audience.getMerchantTransactionId())) {
            throw new ValidationException("Merchant transaction id is invalid");
        }
        if (Utils.isStringEmptyOrBlank(audience.getEventName())) {
            throw new ValidationException("Event name is invalid");
        }
        if (Utils.isStringEmptyOrBlank(audience.getStatus().toString())) {
            throw new ValidationException("Status is invalid");
        }
        if (audience.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Amount is invalid");
        }
    }
}
