package com.esportslan.microservices.esportslanapi.servicehelpers;

import com.esportslan.microservices.esportslanapi.exceptions.ValidationException;
import com.esportslan.microservices.esportslanapi.models.Event;
import com.esportslan.microservices.esportslanapi.utilities.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EventServiceHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceHelper.class);

    public void validateEvent(Event event) {
        LOGGER.info("Validating event object");

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
}
