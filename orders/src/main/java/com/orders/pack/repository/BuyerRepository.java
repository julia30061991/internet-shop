package com.orders.pack.repository;

import com.orders.pack.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    @Override
    List<Buyer> findAll();

    Buyer findBuyerByBuyerId(int id);

    Buyer findBuyerByFullNameAndPhoneNumber(String name, String phone);
}
