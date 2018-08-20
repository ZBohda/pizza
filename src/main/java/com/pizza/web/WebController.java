package com.pizza.web;


import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.domain.entities.Basket;
import com.pizza.services.BasketService;
import com.pizza.services.CurrencyService;
import com.pizza.services.PriceRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes(types = Basket.class)

@Controller
public class WebController {

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BasketService basketService;

    @ModelAttribute("basket")
    public Basket createBasket(HttpSession session) {
        if (session.getAttribute("basket") == null) {
            return new Basket();
        } else return (Basket) session.getAttribute("basket");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        return "index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenuPage(Model model, @ModelAttribute("basket") Basket basket) {
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(basket.getCurrency().getCode()));
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        model.addAttribute("menu", menu);
        return "menu";
    }

    @RequestMapping(value = "/currency/{currencyId}", method = RequestMethod.GET)
    public String changeCurrentCurrency(Model model, @ModelAttribute Basket basket, @PathVariable long currencyId) {
        basketService.recalculateBasketProductsForNewCurrency(basket, currencyService.getCurrencyById(currencyId));
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(basket.getCurrency().getCode()));
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        model.addAttribute("menu", menu);
        return "menu";
    }


}
