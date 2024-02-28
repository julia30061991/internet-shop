package com.shipping.microservice.controller;

import com.orders.pack.model.Order;
import com.shipping.microservice.service.ShippingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final ShippingServiceImpl shippingService;

    public RestController(ShippingServiceImpl shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("/order/shipped/{id}")
    public ResponseEntity<Object> wasShipped(@PathVariable int id) {
        try {
            Order order = shippingService.getPayedOrder(id);
            if (order.getOrderId() == id) {
                shippingService.sendMessage(order);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new Exception("Earlier you need payed your order!");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}