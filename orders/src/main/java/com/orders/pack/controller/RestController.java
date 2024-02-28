package com.orders.pack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orders.pack.config.KafkaSender;
import com.orders.pack.model.Order;
import com.orders.pack.repository.OrderRepository;
import com.orders.pack.service.BuyerServiceImpl;
import com.orders.pack.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final String TOPIC_NAME = "new_orders";

    private final OrderServiceImpl orderService;

    private final BuyerServiceImpl buyerService;

    private final KafkaSender kafkaSender;

    private final OrderRepository orderRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public RestController(OrderServiceImpl orderService, BuyerServiceImpl buyerService, KafkaSender kafkaSender, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.buyerService = buyerService;
        this.kafkaSender = kafkaSender;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> getListOrders() {
        return orderService.getListOrders();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") int id) {
        try {
           Order order = orderService.getOrder(id);
           return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/create")
    public ResponseEntity<Object> createOrder(@RequestParam String name, int id) {
        try {
            Order createdOrder = orderService.createOrder(name, id);
            Order orderFromRepo = orderRepository.findOrderByOrderId(createdOrder.getOrderId());
            kafkaSender.sendMessage(TOPIC_NAME, mapper.writeValueAsString(orderFromRepo));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buyer/create")
    public ResponseEntity<String> createBuyer(@RequestParam String name, String phone, String address) {
        try {
            buyerService.createBuyer(name, phone, address);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/order/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") int id, @RequestParam String status) {
        try {
            orderService.updateOrder(id, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}