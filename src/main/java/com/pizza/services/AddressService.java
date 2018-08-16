package com.pizza.services;

import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Customer;
import com.pizza.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addNewAddressesToCustomer(Address address, Customer customer) {
        address.setCustomer(customer);
        addressRepository.create(address);
    }

    public Address findAddressById(long id) {
        return addressRepository.getAddressById(id);
    }

    public Address getAddressForShadowCustomerId(long id) {
        return addressRepository.getAddressForShadowCustomerId(id);
    }

    public Map<Long, String> getAllAddressesForCustomerId(long id) {
        List<Address> addresses = addressRepository.getAllAddressesForCustomerId(id);
        Map<Long, String> addressMap = new HashMap<>();
        for (Address address : addresses) {
            addressMap.put(address.getId(), address.getCity() + " " + address.getAddress());
        }
        return addressMap;
    }

}
