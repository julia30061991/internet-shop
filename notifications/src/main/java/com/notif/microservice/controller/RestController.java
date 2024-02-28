package com.notif.microservice.controller;

import com.notif.microservice.service.NotificationSenderImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final NotificationSenderImpl notificationSender;

    public RestController(NotificationSenderImpl notificationSender) {
        this.notificationSender = notificationSender;
    }

    @GetMapping("/order/mailing")
    public ResponseEntity<Object> sendNotification() {
        try {
            notificationSender.sendNotification();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}