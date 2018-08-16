package com.pizza.services;

import com.pizza.domain.dto.UnregisteredUserFormDTO;
import com.pizza.domain.entities.Address;
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

    public Customer createNewCustomerFromUnregisteredUserFormDTO(UnregisteredUserFormDTO unregisteredUserFormDTO) {
        Customer customer = new Customer();
        customer.setFirstName(unregisteredUserFormDTO.getFirstName());
        customer.setLastName(unregisteredUserFormDTO.getLastName());
        customer.setPhone(unregisteredUserFormDTO.getPhone());
        Address address = new Address();
        address.setCity(unregisteredUserFormDTO.getCity());
        address.setAddress(unregisteredUserFormDTO.getAddress());
        address.setCustomer(customer);
        customer.getAddresses().add(address);
        customerRepository.create(customer);
        return customer;
    }
}
