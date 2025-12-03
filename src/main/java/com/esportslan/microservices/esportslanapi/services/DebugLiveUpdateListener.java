//package com.esportslan.microservices.esportslanapi.services;
//
//import com.esportslan.microservices.esportslanapi.configurations.RabbitTestConfig;
//import com.esportslan.microservices.esportslanapi.models.UpdateRequestEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DebugLiveUpdateListener {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DebugLiveUpdateListener.class);
//
//    @RabbitListener(queues = RabbitTestConfig.TEST_QUEUE)
//    public void onMessage(UpdateRequestEvent event) {
//        LOGGER.info("Received from RabbitMQ: " + event.getCategory() + " | " + event.getTitle());
//    }
//}
