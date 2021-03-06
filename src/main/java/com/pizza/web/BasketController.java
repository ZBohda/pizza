package com.pizza.web;

import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.domain.dto.RegisteredUserFormDTO;
import com.pizza.domain.dto.UnregisteredUserFormDTO;
import com.pizza.domain.entities.Basket;
import com.pizza.domain.entities.Currency;
import com.pizza.domain.entities.Customer;
import com.pizza.services.*;
import com.pizza.validators.UnregisterUserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes(types = Basket.class)

@Controller
@RequestMapping(value = "/basket/")
public class BasketController {

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UnregisterUserFormValidator unregisterUserFormValidator;

    @ModelAttribute("basket")
    public Basket createBasket(HttpSession session) {
        if (session.getAttribute("basket") == null) {
            return new Basket();
        } else return (Basket) session.getAttribute("basket");
    }

    @RequestMapping(value = "/product/{priceRowId}/add", method = RequestMethod.POST)
    public String addProductToBasket(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(basket.getCurrency().getCode()));
        model.addAttribute("menu", menu);
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        return "menu";
    }

    @RequestMapping(value = "/product/{priceRowId}/increase", method = RequestMethod.GET)
    public String increaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket, HttpSession session) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (session.getAttribute("userId") != null) {
            model.addAttribute("addressesMap", addressService.getAllAddressesForCustomerId((Long) session.getAttribute("userId")));
            model.addAttribute("registeredUserFormDTO", new RegisteredUserFormDTO());
        }
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/decrease", method = RequestMethod.GET)
    public String decreaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket, HttpSession session) {
        basket.decreasePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (session.getAttribute("userId") != null) {
            model.addAttribute("addressesMap", addressService.getAllAddressesForCustomerId((Long) session.getAttribute("userId")));
            model.addAttribute("registeredUserFormDTO", new RegisteredUserFormDTO());
        }
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/remove", method = RequestMethod.GET)
    public String removeProduct(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket, HttpSession session) {
        basket.removePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (session.getAttribute("userId") != null) {
            model.addAttribute("addressesMap", addressService.getAllAddressesForCustomerId((Long) session.getAttribute("userId")));
            model.addAttribute("registeredUserFormDTO", new RegisteredUserFormDTO());
        }
        return "basket";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getBasket(Model model, @ModelAttribute Basket basket, HttpSession session) {
        model.addAttribute("basket", basket);
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (session.getAttribute("userId") != null) {
            model.addAttribute("addressesMap", addressService.getAllAddressesForCustomerId(Long.parseLong(session.getAttribute("userId").toString())));
            model.addAttribute("registeredUserFormDTO", new RegisteredUserFormDTO());
        } else {
            model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        }
        return "basket";
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public String placeOrder(@ModelAttribute("unregisteredUserFormDTO") @Validated UnregisteredUserFormDTO unregisteredUserFormDTO, BindingResult result,
                             @ModelAttribute("registeredUserFormDTO") RegisteredUserFormDTO registeredUserFormDTO,
                             HttpSession session, Model model, @ModelAttribute("basket") Basket basket) {
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (session.getAttribute("userId") != null) {
            orderService.createNewOrderFromBasket(basket, Long.parseLong(session.getAttribute("userId").toString()), Long.parseLong(registeredUserFormDTO.getAddressId()));
            model.addAttribute("orders", orderService.getAllOrdersForCustomerId((Long) session.getAttribute("userId")));
            return "customer-orders";
        } else {
            unregisterUserFormValidator.validate(unregisteredUserFormDTO, result);
            if (result.hasErrors()) {
                model.addAttribute("basket", basket);
                model.addAttribute("unregisteredUserFormDTO", unregisteredUserFormDTO);
                return "basket";
            } else {
                Customer customer = customerService.createNewCustomerFromUnregisteredUserFormDTO(unregisteredUserFormDTO);
                orderService.createNewOrderFromBasket(basket, customer.getId(), addressService.getAddressForShadowCustomerId(customer.getId()).getId());
                List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(basket.getCurrency().getCode()));
                model.addAttribute("menu", menu);
                return "menu";
            }
        }
    }
}