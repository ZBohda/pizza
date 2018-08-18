package com.pizza.web;

import com.pizza.domain.entities.Order;
import com.pizza.services.CurrencyService;
import com.pizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/customer/")
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model, HttpSession session) {
        model.addAttribute("orders", orderService.getAllOrdersForCustomerId((Long) session.getAttribute("userId")));
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        return "customer-orders";
    }

    @RequestMapping(value = "/orders/{orderId}/details", method = RequestMethod.GET)
    public String getOrderDetails(Model model, @PathVariable long orderId) {
        Order order = orderService.getOrderByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderEntry", order.getEntries());
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        return "customer-orders-details";
    }
}
