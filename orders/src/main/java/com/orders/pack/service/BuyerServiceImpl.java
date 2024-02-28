package com.orders.pack.service;

import com.orders.pack.model.Buyer;
import com.orders.pack.repository.BuyerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public void createBuyer(String fullname, String phone, String address) throws Exception {
        if(fullname == null || phone == null || address == null)
        {
            throw new Exception("No one field does not be empty");
        }
        Buyer buyer = buyerRepository.findBuyerByFullNameAndPhoneNumber(fullname, phone);
        if(buyer != null) {
            throw new Exception("This buyer is exist");
        } else {
            buyer = new Buyer(fullname, phone, address);
            buyerRepository.save(buyer);
        }
    }
}
