package com.pay.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orders.pack.model.Order;
import com.orders.pack.model.Status;
import com.orders.pack.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    private Order order = new Order();

    private final String TOPIC_NAME = "payed_orders";

    private final ObjectMapper mapper = new ObjectMapper();

    private final OrderRepository orderRepository;

    private final PaySender paySender;

    public PayServiceImpl(OrderRepository orderRepository, PaySender paySender) {
        this.orderRepository = orderRepository;
        this.paySender = paySender;
    }

    @KafkaListener(topics = "new_orders", groupId = "new_orders_consumers")
    public void listen(String message) throws Exception {
        log.info("listen message: " + message);
        order = mapper.readValue(message, Order.class);
    }

    @Override
    public Order getOrderForPay() {
        log.info("Start pay : ");
        //логика обработка оплаты
        order.setStatus(Status.PAIDED);
        orderRepository.save(order);
        log.info("Order was payed and save");
        return order;
    }

    @Override
    public void sendMessageAboutPay(Order order) throws Exception {
        paySender.sendMessage(TOPIC_NAME, mapper.writeValueAsString(order));
        log.info("Message about payment send to broker");
    }
}
