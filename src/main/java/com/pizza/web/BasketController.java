package com.pizza.web;

import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Basket;
import com.pizza.domain.entities.Customer;
import com.pizza.services.PriceRowService;
import com.pizza.services.ProductService;
import com.pizza.validators.RegisterFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    @RequestMapping(value = "/product/{priceRowId}/increase", method = RequestMethod.GET)
    public String increaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/decrease", method = RequestMethod.GET)
    public String decreaseProductQuantity(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.decreasePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/remove", method = RequestMethod.GET)
    public String removeProduct(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.removePriceRow(priceRowService.getPriceRowById(priceRowId));
        model.addAttribute("basket", basket);
        return "basket";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getBasket(Model model, @ModelAttribute Basket basket) {
        model.addAttribute("basket", basket);
        model.addAttribute("registerFormDTO", new RegisterFormDTO());
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
        if (session.getAttribute("userId") == null) {

            return "index";
        } else return "index";
    }
}