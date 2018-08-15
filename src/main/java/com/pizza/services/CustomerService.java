package com.pizza.services;

import com.pizza.domain.entities.Customer;
import com.pizza.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerById(long id) {
        return customerRepository.read(id);
    }
}
