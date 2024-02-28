package com.pay.service.controller;

import com.orders.pack.model.Order;
import com.pay.service.service.PayServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final PayServiceImpl payService;

    public RestController(PayServiceImpl payService) {
        this.payService = payService;
    }

    @GetMapping("/order/pay/{id}")
    public ResponseEntity<Object> wasPayed(@PathVariable int id) {
        try {
            Order order = payService.getOrderForPay();
            if (order.getOrderId() == id) {
                payService.sendMessageAboutPay(order);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new Exception("Order with this id is not ready, try later");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}