package com.orders.pack.service;

import com.orders.pack.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(String name, int buyerId) throws Exception;

    void updateOrder(int id, String status) throws Exception;

    Order getOrder(int id) throws Exception;

    List<Order> getListOrders();
}
