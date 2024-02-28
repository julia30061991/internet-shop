package com.notif.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationSenderImpl implements NotificationSender {

    private String messageToBuyer = "";

    @KafkaListener(topics = "sent_orders", groupId = "sent_orders_consumers")
    public void listen(String message) {
        log.info("listen message: " + message);
        messageToBuyer = message;
    }

    @Override
    public void sendNotification() {
        //логика отправки уведомления покупателю - на email/личный кабинет/в мессенджер
    }
}
