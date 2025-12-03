//package com.esportslan.microservices.esportslanapi.configurations;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitTestConfig {
//    public static final String TEST_QUEUE = "test_liveupdates";
//
//    @Bean
//    public Queue testQueue() {
//        return new Queue(TEST_QUEUE);
//    }
//
//    @Bean
//    public Binding testBinding(Queue testQueue, TopicExchange tournamentEventsExchange) {
//        return BindingBuilder.bind(testQueue)
//                .to(tournamentEventsExchange)
//                .with("liveupdate.*");
//    }
//}
