package com.pizza.web;

import com.pizza.domain.dto.ProductFormDTO;
import com.pizza.domain.entities.Currency;
import com.pizza.domain.entities.Customer;
import com.pizza.domain.entities.Order;
import com.pizza.domain.entities.Product;
import com.pizza.services.PriceRowService;
import com.pizza.services.ProductService;
import com.pizza.validators.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private ProductFormValidator productFormValidator;

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceRowService priceRowService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(productFormValidator);
    }

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
        List<Currency> currencies = new ArrayList<>();
        model.addAttribute("currencies", currencies);
        return "admin-currencies";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.GET)
    public String getProductAddFrom(Model model) {
        model.addAttribute("productFormDTO", new ProductFormDTO());
        return "admin-menu-add";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    public String addNewProduct(@ModelAttribute("productFormDTO") @Validated ProductFormDTO productFormDTO, BindingResult result, Model model) {
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
}
