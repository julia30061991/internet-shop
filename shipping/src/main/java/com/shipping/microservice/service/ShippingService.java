package com.shipping.microservice.service;

import com.orders.pack.model.Order;

public interface ShippingService {

    Order getPayedOrder(int id);

    void sendMessage(Order order);
}
