package com.pizza.web;

import com.pizza.domain.dto.AddressFormDTO;
import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Order;
import com.pizza.services.*;
import com.pizza.validators.AddressFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressFormValidator addressFormValidator;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model, HttpSession session) {
        model.addAttribute("orders", orderService.getAllOrdersForCustomerId((Long) session.getAttribute("userId")));
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        return "customer-orders";
    }

    @RequestMapping(value = "/orders/{orderId}/details", method = RequestMethod.GET)
    public String getOrderDetails(Model model, @PathVariable long orderId) {
        Order order = orderService.getOrderByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderEntry", order.getEntries());
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        return "customer-orders-details";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model, HttpSession session) {
        model.addAttribute("customer", customerService.findCustomerById((Long) session.getAttribute("userId")));
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        model.addAttribute("addressFormDTO", new AddressFormDTO());
        return "customer-profile";
    }

    @RequestMapping(value = "/address/add", method = RequestMethod.POST)
    public String addNewAddress(Model model, HttpSession session, @ModelAttribute("addressFormDTO") @Validated AddressFormDTO addressFormDTO, BindingResult result) {
        addressFormValidator.validate(addressFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("customer", customerService.findCustomerById((Long) session.getAttribute("userId")));
            model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
            model.addAttribute("addressFormDTO", new AddressFormDTO());
        } else {
            customerService.addNewAddressToCustomerFromAddressFormDTO(addressFormDTO, (Long) session.getAttribute("userId"));
            model.addAttribute("customer", customerService.findCustomerById((Long) session.getAttribute("userId")));
            model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
            model.addAttribute("addressFormDTO", new AddressFormDTO());
        }
        return "customer-profile";
    }

    @RequestMapping(value = "/address/{addressId}/switch", method = RequestMethod.POST)
    public String changeAddressStatus(Model model, @PathVariable long addressId, HttpSession session) {
        addressService.changeAddressStatusToOpposite(addressId);
        model.addAttribute("customer", customerService.findCustomerById((Long) session.getAttribute("userId")));
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        model.addAttribute("addressFormDTO", new AddressFormDTO());
        return "customer-profile";
    }



}
