package com.orders.pack.service;

import com.orders.pack.model.Buyer;
import com.orders.pack.model.Order;
import com.orders.pack.model.Status;
import com.orders.pack.repository.BuyerRepository;
import com.orders.pack.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BuyerRepository buyerRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Order createOrder(String name, int buyerId) throws Exception {
        Order order = new Order();
        order.setName(name);
        order.setStatus(Status.ACCEPTED);
        Buyer buyer = buyerRepository.findBuyerByBuyerId(buyerId);
        if (buyer != null) {
            order.setBuyer(buyer);
        } else {
            throw new Exception("Buyer does not found. Create the buyer");
        }
        orderRepository.save(order);
        return order;
    }

    @Override
    public void updateOrder(int id, String status) throws Exception {
        Order order = orderRepository.findOrderByOrderId(id);
        if (order != null) {
            switch (status) {
                case "PAIDED":
                    order.setStatus(Status.PAIDED);
                    break;
                case "COMPLETED":
                    order.setStatus(Status.COMPLETED);
                    break;
            }
        } else {
            throw new Exception("Order with this id does not found");
        }
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(int id) throws Exception {
        Order order = orderRepository.findOrderByOrderId(id);
        if (order != null) {
            return order;
        } else {
            throw new Exception("Order with this id does not found");
        }
    }

    @Override
    public List<Order> getListOrders() {
        return orderRepository.findAll();
    }
}