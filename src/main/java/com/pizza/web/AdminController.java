package com.pizza.web;

import com.pizza.domain.dto.CurrencyFormDTO;
import com.pizza.domain.dto.ProductFormDTO;
import com.pizza.domain.entities.Customer;
import com.pizza.domain.entities.Order;
import com.pizza.services.CurrencyService;
import com.pizza.services.PriceRowService;
import com.pizza.services.ProductService;
import com.pizza.validators.CurrencyFormValidator;
import com.pizza.validators.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private ProductFormValidator productFormValidator;

    @Autowired
    private CurrencyFormValidator currencyFormValidator;

    @Autowired
    private ProductService productService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private PriceRowService priceRowService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin-menu";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<Customer> customers = new ArrayList<>();
        model.addAttribute("customers", customers);
        return "admin-users";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        List<Order> orders = new ArrayList<>();
        model.addAttribute("orders", orders);
        return "admin-orders";
    }

    @RequestMapping(value = "/currencies", method = RequestMethod.GET)
    public String getCurrency(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        return "admin-currencies";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.GET)
    public String getProductAddFrom(Model model) {
        model.addAttribute("productFormDTO", new ProductFormDTO());
        return "admin-menu-add";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    public String addNewProduct(@ModelAttribute("productFormDTO") @Validated ProductFormDTO productFormDTO, BindingResult result, Model model) {
        productFormValidator.validate(productFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute(productFormDTO);
            return "admin-menu-add";
        } else {
            productService.addNewProduct(productFormDTO);
            model.addAttribute("products", productService.getAll());
            return "admin-menu";
        }
    }

    @RequestMapping(value = "/product/{productId}/prices", method = RequestMethod.GET)
    public String findProductsPrices(@PathVariable long productId, Model model) {
        model.addAttribute("prices", priceRowService.getAllPriceRowsForProduct(productId));
        return "admin-menu";
    }

    @RequestMapping(value = "/currencies/add", method = RequestMethod.GET)
    public String getCurrencyAddForm(Model model){
        model.addAttribute("currencyFormDTO", new CurrencyFormDTO());
        return "admin-currency-add";
    }

    @RequestMapping(value = "/currencies/add", method = RequestMethod.POST)
    public String addNewCurrency(@ModelAttribute("currencyFormDTO") @Validated CurrencyFormDTO currencyFormDTO, BindingResult result, Model model) {
        currencyFormValidator.validate(currencyFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute(currencyFormDTO);
            return "admin-currency-add";
        } else {
            currencyService.addNewCurrency(currencyFormDTO);
            model.addAttribute("currencies", currencyService.getAll());
            return "admin-currencies";
        }
    }
}
