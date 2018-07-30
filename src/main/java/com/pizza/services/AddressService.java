package com.pizza.services;

import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Customer;
import com.pizza.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addNewAddressesToCustomer(Address address, Customer customer) {
        address.setCustomer(customer);
        addressRepository.create(address);
    }
}
