package com.pizza.web;

import com.pizza.domain.dto.OrderStateDTO;
import com.pizza.domain.dto.PriceRowFormDTO;
import com.pizza.domain.dto.ProductFormDTO;
import com.pizza.domain.entities.*;
import com.pizza.domain.enums.OrderState;
import com.pizza.services.*;
import com.pizza.validators.CurrencyValidator;
import com.pizza.validators.PriceRowFormValidator;
import com.pizza.validators.ProductFormValidator;
import com.sun.javafx.sg.prism.NGShape;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private ProductFormValidator productFormValidator;

    @Autowired
    private CurrencyValidator currencyValidator;

    @Autowired
    private PriceRowFormValidator priceRowFormValidator;

    @Autowired
    private ProductService productService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin-menu";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("accounts", accountService.findAllCustomersAccount());
        return "admin-users";
    }

    @RequestMapping(value = "/user/{accountId}/switch", method = RequestMethod.POST)
    public String activateAccount(@PathVariable long accountId, Model model) {
        accountService.changeAccountStatusToOpposite(accountId);
        model.addAttribute("accounts", accountService.findAllCustomersAccount());
        return "admin-users";
    }

    @RequestMapping(value = "/user/{accountId}/addresses", method = RequestMethod.GET)
    public String getUserAddresses(@PathVariable long accountId, Model model) {
        Account account = accountService.findAccountById(accountId);
        model.addAttribute("account", account);
        return "admin-user-addresses";
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("orderStates", orderService.getOrderStates());
        return "admin-orders";
    }

    @RequestMapping(value = "/order/{orderId}/details", method = RequestMethod.GET)
    public String getOrderDetails(Model model, @PathVariable long orderId) {
        model.addAttribute("order", orderService.getOrderByOrderId(orderId));
        model.addAttribute("orderStates", orderService.getOrderStates());
        return "admin-order-details";
    }

    @RequestMapping(value = "/order/{orderId}/state/{stateId}/change", method = RequestMethod.POST)
    public String changeOrderState(Model model, @PathVariable long orderId, @PathVariable Integer stateId){
        Map<Integer, OrderState> orderStateMap = orderService.getOrderStates();
        Order order = orderService.getOrderByOrderId(orderId);
        order.setOrderState(orderStateMap.get(stateId));
        orderService.update(order);
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("orderStates", orderService.getOrderStates());
        return "admin-orders";
    }


    @RequestMapping(value = "/currencies", method = RequestMethod.GET)
    public String getCurrency(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        return "admin-currencies";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String getProductAddFrom(Model model) {
        model.addAttribute("productFormDTO", new ProductFormDTO());
        return "admin-product-add";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addNewProduct(@ModelAttribute("productFormDTO") @Validated ProductFormDTO productFormDTO, BindingResult result, Model model) {
        productFormValidator.validate(productFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute(productFormDTO);
            return "admin-product-add";
        } else {
            productService.addNewProduct(productFormDTO);
            model.addAttribute("products", productService.getAll());
            return "admin-menu";
        }
    }

    @RequestMapping(value = "/product/{productId}/update", method = RequestMethod.GET)
    public String getProductUpdateForm(@PathVariable long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("productFormDTO", productService.getProductFormDTOFromProduct(product));
        byte[] encodedPicture = Base64.encode(product.getPicture());
        try {
            model.addAttribute("image", new String(encodedPicture, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        }
        return "admin-product-update";
    }

    @RequestMapping(value = "/product/{productId}/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("productFormDTO") @Validated ProductFormDTO productFormDTO, @PathVariable long productId, BindingResult result, Model model) {
        productFormValidator.validate(productFormDTO, result);
        if (result.hasErrors()) {
            Product product = productService.getProduct(productId);
            model.addAttribute("product", productService.getProduct(productId));
            model.addAttribute(productFormDTO);
            byte[] encodedPicture = Base64.encode(product.getPicture());
            try {
                model.addAttribute("image", new String(encodedPicture, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOG.error(e);
            }
            return "admin-product-update";
        } else {
            productService.updateProductFromProductFormDTO(productFormDTO, productId);
            model.addAttribute("products", productService.getAll());
            return "admin-menu";
        }
    }

    @RequestMapping(value = "/product/{productId}/prices", method = RequestMethod.GET)
    public String findProductsPrices(@PathVariable long productId, Model model) {
        model.addAttribute("priceRows", priceRowService.getAllPriceRowsForProduct(productId));
        return "admin-product-prices";
    }

    @RequestMapping(value = "/product/{productId}/price/add", method = RequestMethod.GET)
    public String getAddPriceForm(@PathVariable long productId, Model model) {
        model.addAttribute("priceRowFormDTO", new PriceRowFormDTO());
        model.addAttribute("productId", productId);
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        return "admin-product-price-add";
    }

    @RequestMapping(value = "/product/{productId}/price/add", method = RequestMethod.POST)
    public String addNewPriceRow(@ModelAttribute("priceRowFormDTO") @Validated PriceRowFormDTO priceRowFormDTO, @PathVariable long productId, BindingResult result, Model model) {
        priceRowFormValidator.validate(priceRowFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("priceRowFormDTO", priceRowFormDTO);
            model.addAttribute("productId", productId);
            model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
            return "admin-product-price-add";
        } else {
            priceRowService.addNewPriceRowFromPriceRowFormDTO(priceRowFormDTO, productId);
            model.addAttribute("priceRows", priceRowService.getAllPriceRowsForProduct(productId));
            return "admin-product-prices";
        }
    }

    @RequestMapping(value = "/product/{productId}/update/{priceRowId}", method = RequestMethod.GET)
    public String getUpdatePriceRowForm(@PathVariable long priceRowId, @PathVariable long productId, Model model) {
        PriceRow priceRow = priceRowService.getPriceRowById(priceRowId);
        PriceRowFormDTO priceRowFormDTO = priceRowService.getPriceRowFormDTOFromPriceRow(priceRow);
        model.addAttribute("priceRowFormDTO", priceRowFormDTO);
        model.addAttribute("priceRowId", priceRowId);
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        return "admin-product-price-update";
    }

    @RequestMapping(value = "/product/{productId}/update/{priceRowId}", method = RequestMethod.POST)
    public String updatePriceRowForm(@ModelAttribute("priceRowFormDTO") @Validated PriceRowFormDTO priceRowFormDTO, @PathVariable long priceRowId, @PathVariable long productId, BindingResult result, Model model) {
        priceRowFormValidator.validate(priceRowFormDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("priceRowFormDTO", priceRowFormDTO);
            model.addAttribute("priceRowId", priceRowId);
            model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
            return "admin-product-price-update";
        } else {
            priceRowService.updatePriceRowFromPriceRowFormDTO(priceRowFormDTO, priceRowId);
            model.addAttribute("priceRows", priceRowService.getAllPriceRowsForProduct(productId));
            return "admin-product-prices";
        }
    }

    @RequestMapping(value = "/currencies/add", method = RequestMethod.GET)
    public String getCurrencyAddForm(Model model) {
        model.addAttribute("currency", new Currency());
        return "admin-currencies-add";
    }

    @RequestMapping(value = "/currencies/add", method = RequestMethod.POST)
    public String addNewCurrency(@ModelAttribute("currency") @Validated Currency currency, BindingResult result, Model model) {
        currencyValidator.validate(currency, result);
        if (result.hasErrors()) {
            model.addAttribute(currency);
            return "admin-currencies-add";
        } else {
            currencyService.addNewCurrency(currency);
            model.addAttribute("currencies", currencyService.getAll());
            return "admin-currencies";
        }
    }

    @RequestMapping(value = "/currencies/{currencyId}/update", method = RequestMethod.GET)
    public String getCurrencyUpdateForm(@PathVariable long currencyId, Model model) {
        model.addAttribute("currency", currencyService.getCurrencyById(currencyId));
        return "admin-currencies-update";
    }

    @RequestMapping(value = "/currencies/{currencyId}/update", method = RequestMethod.POST)
    public String updateCurrency(@ModelAttribute("currency") @Validated Currency currency, BindingResult result, Model model) {
        currencyValidator.validate(currency, result);
        if (result.hasErrors()) {
            model.addAttribute(currency);
            return "admin-currencies-update";
        } else {
            currencyService.updateCurrency(currency);
            model.addAttribute("currencies", currencyService.getAll());
            return "admin-currencies";
        }
    }
}
