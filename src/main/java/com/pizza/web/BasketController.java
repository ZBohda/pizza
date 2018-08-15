package com.pizza.web;

import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.domain.dto.UnregisteredUserFormDTO;
import com.pizza.domain.entities.Basket;
import com.pizza.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes(types = Basket.class)

@Controller
@RequestMapping(value = "/basket/")
public class BasketController {

    private static final String DEFAULT_CODE = "USD";

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    @RequestMapping(value = "/product/{priceRowId}/increase", method = RequestMethod.GET)
    public String increaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/decrease", method = RequestMethod.GET)
    public String decreaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.decreasePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/remove", method = RequestMethod.GET)
    public String removeProduct(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.removePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        return "basket";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getBasket(Model model, @ModelAttribute Basket basket) {
        model.addAttribute("basket", basket);
        model.addAttribute("unregisteredUserFormDTO", new UnregisteredUserFormDTO());
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/add", method = RequestMethod.POST)
    public String addProductToBasket(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
        model.addAttribute("menu", menu);
        return "menu";
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public String placeOrder(Model model, @ModelAttribute Basket basket,  HttpSession session) {
        if (session.getAttribute("userId") != null) {
            orderService.createNewOrderFromBasket(basket, (Long) session.getAttribute("userId"));
            basket.clear();
            List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
            model.addAttribute("menu", menu);
            return "menu";
        } else {

            model.addAttribute("registerFormDTO", new RegisterFormDTO());
            return "register";
        }
    }
}