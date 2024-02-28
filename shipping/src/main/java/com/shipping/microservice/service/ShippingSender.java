package com.shipping.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippingSender {

    private final KafkaTemplate <String, String> kafkaTemplate;

    public ShippingSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String message) {
        log.info("Sending message : ", message);
        kafkaTemplate.send(topicName, message);
    }
}