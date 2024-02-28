package com.pay.service.service;

import com.orders.pack.model.Order;

public interface PayService {

    Order getOrderForPay();

    void sendMessageAboutPay(Order order) throws Exception;
}