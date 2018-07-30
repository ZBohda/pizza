package com.pizza.web;

import com.pizza.domain.dto.ProductFormDTO;
import com.pizza.domain.entities.Currency;
import com.pizza.domain.entities.Customer;
import com.pizza.domain.entities.Order;
import com.pizza.domain.entities.Product;
import com.pizza.validators.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private ProductFormValidator productFormValidator;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu(Model model) {
        List<Product> products = new ArrayList<>();
        model.addAttribute("products", products);
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
    public String addNewProduct(@ModelAttribute("productFormDTO") @Validated ProductFormDTO productFormDTO, @RequestParam("file") MultipartFile file, Model model) {
        if (false) {
            model.addAttribute(productFormDTO);
            return "admin-menu-add";
        } else {
            System.out.println(file.getOriginalFilename());
            return "index";
        }
    }
}
