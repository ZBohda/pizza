package com.pizza.services;

import com.pizza.domain.entities.Basket;
import com.pizza.domain.entities.Order;
import com.pizza.domain.entities.OrderEntry;
import com.pizza.domain.entities.PriceRow;
import com.pizza.domain.enums.OrderState;
import com.pizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CurrencyService currencyService;

    public void update(Order order) {
        orderRepository.update(order);
    }

    public void createNewOrderFromBasket(Basket basket, long customerId, long addressId) {
        Order order = new Order();
        order.setCreationTime(new Date());
        order.setOrderState(OrderState.PLACED);
        order.setCustomer(customerService.findCustomerById(customerId));
        order.setTotalPrice(basket.getTotalPrice());
        order.setAddress(addressService.findAddressById(addressId));
        order.setCurrency(currencyService.getCurrencyByCode(basket.getCurrency().getCode()));
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

    public final Map<Integer, OrderState> getAllOrderStates() {
        Map<Integer, OrderState> orderStates = new HashMap<>();
        orderStates.put(1, OrderState.PLACED);
        orderStates.put(2, OrderState.VERIFIED);
        orderStates.put(3, OrderState.IN_PROGRESS);
        orderStates.put(4, OrderState.FINISHED);
        orderStates.put(5, OrderState.CANCELED);
        return orderStates;
    }

    public final Map<Integer, OrderState> getAllowedStatesForCertainState(OrderState certainState) {
        Map<Integer, OrderState> allowedStates = new HashMap<>();
        if(certainState.equals(OrderState.PLACED)){
            allowedStates.put(2, OrderState.VERIFIED);
            allowedStates.put(5, OrderState.CANCELED);
            return allowedStates;
        }
        if(certainState.equals(OrderState.VERIFIED)){
            allowedStates.put(3, OrderState.IN_PROGRESS);
            allowedStates.put(5, OrderState.CANCELED);
            return allowedStates;
        }
        if(certainState.equals(OrderState.IN_PROGRESS)){
            allowedStates.put(4, OrderState.FINISHED);
            allowedStates.put(5, OrderState.CANCELED);
            return allowedStates;
        }
        return allowedStates;
    }

    public List<Order> getAllOrdersForCustomerId(long customerId) {
        return orderRepository.findAllOrdersForCustomerId(customerId);
    }

    public Order getOrderByOrderId(long orderId) {
        return orderRepository.read(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
