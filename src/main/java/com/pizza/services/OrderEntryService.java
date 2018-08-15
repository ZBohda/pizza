package com.pizza.services;

import com.pizza.domain.entities.OrderEntry;
import com.pizza.repository.OrderEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEntryService {

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    public void createNewOrderEntry(OrderEntry orderEntry){
        orderEntryRepository.create(orderEntry);
    }

    public void updateOrderEntry(OrderEntry orderEntry){
        orderEntryRepository.update(orderEntry);
    }
}
