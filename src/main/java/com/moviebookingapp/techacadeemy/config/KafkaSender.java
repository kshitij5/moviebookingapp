//package com.moviebookingapp.techacadeemy.config;
//
//import com.moviebookingapp.techacadeemy.entities.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaSender {
//
//    Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void sendMessage(Object data, String topicName){
//
//        LOGGER.info(String.format("Message sent -> %s", data.toString()));
//
//        Message<Object> message = MessageBuilder
//                .withPayload(data)
//                .setHeader(KafkaHeaders.TOPIC, topicName)
//                .build();
//
//        kafkaTemplate.send(message);
//    }
//}