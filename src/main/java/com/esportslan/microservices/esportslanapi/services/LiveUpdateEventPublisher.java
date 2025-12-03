package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.EmailClientHelper;
import com.esportslan.microservices.esportslanapi.configurations.RabbitConfig;
import com.esportslan.microservices.esportslanapi.exceptions.EventPublisherException;
import com.esportslan.microservices.esportslanapi.models.UpdateRequestEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LiveUpdateEventPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveUpdateEventPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public LiveUpdateEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(UpdateRequestEvent event) {
        LOGGER.info("Publishing event: " + event.toString());
        String routingKey = switch (event.getCategory()) {
            case SCHEDULE -> RabbitConfig.RK_LIVE_UPDATE_SCHEDULE;
            case RESULT   -> RabbitConfig.RK_LIVE_UPDATE_RESULT;
            case AWARD    -> RabbitConfig.RK_LIVE_UPDATE_AWARD;
        };
        try {
            rabbitTemplate.convertAndSend(
                    RabbitConfig.TOURNAMENT_EVENTS_EXCHANGE,
                    routingKey,
                    event
            );
        } catch (AmqpException exception) {
            LOGGER.error("Failed publishing event (eid={}): {}", event.getEventId(), exception.getMessage(), exception);
            if (exception.getCause() != null) {
                LOGGER.error("Root cause: ", exception.getCause());
            }
            throw new EventPublisherException("Got exception while publishing the event with eid:" + event.getEventId(), exception);
        }
    }
}
