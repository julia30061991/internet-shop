package com.orders.pack.repository;

import com.orders.pack.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    List<Order> findAll();

    Order findOrderByOrderId(int id);
}
