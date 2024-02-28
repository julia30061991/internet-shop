package com.shipping.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orders.pack.model.Order;
import com.orders.pack.model.Status;
import com.orders.pack.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShippingServiceImpl implements ShippingService {

    private final String TOPIC_NAME = "sent_orders";

    private Order order = new Order();

    private final ObjectMapper mapper = new ObjectMapper();

    private final OrderRepository orderRepository;

    private final ShippingSender shippingSender;

    public ShippingServiceImpl(OrderRepository orderRepository, ShippingSender shippingSender) {
        this.orderRepository = orderRepository;
        this.shippingSender = shippingSender;
    }


    @KafkaListener(topics = "payed_orders", groupId = "payed_orders_consumers")
    public void listen(String message) throws Exception {
        log.info("listen message: " + message);
        String value = transformMessage(message);
        order = mapper.readValue(value, Order.class);
    }

    public String transformMessage(String string) {
        String value = string.replaceAll("\\\\", "");
        String result = value.substring(1, value.length() -1);
        return result;
    }

    @Override
    public Order getPayedOrder(int id) {
        log.info("Get info about payed order");
        order.setStatus(Status.COMPLETED);
        orderRepository.save(order);
        log.info("Order was saved");
        //упаковка товара и отправка
        log.info("Order was shipped");
        return order;
    }

    @Override
    public void sendMessage(Order order) {
        String message = "Order with id " + order.getOrderId() + " name: " + order.getName() + " was shipped";
        shippingSender.sendMessage(TOPIC_NAME, message);
        log.info("Message about shipping send to broker");
    }
}
