package com.shipping.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.orders.pack.repository")
@EntityScan("com.orders.pack.model")
@ComponentScan({"com.shipping.microservice.service", "com.shipping.microservice.controller"})
public class ShippingMicroServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ShippingMicroServiceApp.class, args);
    }
}
