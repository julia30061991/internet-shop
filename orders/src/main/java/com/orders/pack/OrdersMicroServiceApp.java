package com.orders.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.orders.pack.config", "com.orders.pack.service", "com.orders.pack.controller"})
@EnableJpaRepositories("com.orders.pack.repository")
@EntityScan("com.orders.pack.model")
public class OrdersMicroServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OrdersMicroServiceApp.class, args);
    }
}
