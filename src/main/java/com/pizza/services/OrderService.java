package com.pizza.services;

import com.pizza.domain.entities.*;
import com.pizza.domain.enums.OrderState;
import com.pizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    public void createNewOrderFromBasket(Basket basket, long customerId, long addressId) {

        Order order = new Order();
        order.setCreationTime(new Date());
        order.setOrderState(OrderState.PLACED);
        order.setCustomer(customerService.findCustomerById(customerId));
        order.setTotalPrice(basket.getTotalPrice());
        order.setAddress(addressService.findAddressById(addressId));

        orderRepository.create(order);

        for (Map.Entry<PriceRow, Integer> entry : basket.getProducts().entrySet()) {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setPriceRow(entry.getKey());
            orderEntry.setQuantity(entry.getValue());
            orderEntry.setProduct(entry.getKey().getProduct());
            order.getEntries().add(orderEntry);
            orderEntry.setOrder(order);
        }
        orderRepository.update(order);
        basket.clear();
    }
}
