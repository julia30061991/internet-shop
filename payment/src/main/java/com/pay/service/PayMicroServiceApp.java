package com.pay.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.orders.pack.repository")
@ComponentScan({"com.pay.service.service", "com.pay.service.controller"})
@EntityScan("com.orders.pack.model")
public class PayMicroServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(PayMicroServiceApp.class, args);
    }
}
