package com.pay.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaySender {

    private final KafkaTemplate <String, String> kafkaTemplate;

    public PaySender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String message) {
        log.info("Sending message : ", message);
        kafkaTemplate.send(topicName, message);
    }
}