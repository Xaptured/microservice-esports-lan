package com.esportslan.microservices.esportslanapi.configurations;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String TOURNAMENT_EVENTS_EXCHANGE = "tournament.events";

    public static final String RK_LIVE_UPDATE_SCHEDULE = "liveupdate.schedule";
    public static final String RK_LIVE_UPDATE_RESULT   = "liveupdate.result";
    public static final String RK_LIVE_UPDATE_AWARD    = "liveupdate.award";

    @Bean
    public TopicExchange tournamentEventsExchange() {
        return new TopicExchange(TOURNAMENT_EVENTS_EXCHANGE);
    }
}
