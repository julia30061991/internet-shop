package com.notif.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.notif.microservice.service", "com.notif.microservice.controller"})
public class NotificMicroServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificMicroServiceApp.class, args);
    }
}
