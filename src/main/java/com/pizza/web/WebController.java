package com.pizza.web;


import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.domain.entities.Basket;
import com.pizza.domain.entities.PriceRow;
import com.pizza.services.AccountService;
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

    private static final String DEFAULT_CODE = "USD";

    @Autowired
    private AccountService accountService;

    @Autowired
    private PriceRowService priceRowService;

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenuPage(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("currency") != null) {
            List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
            model.addAttribute("menu", menu);
        } else {
            List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
            model.addAttribute("menu", menu);
        }
        return "menu";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String getBasket(HttpSession session, Model model) {
        model.addAttribute("basket", session.getAttribute("basket"));
        return "basket";
    }

    @RequestMapping(value = "/product/{priceRowId}/add", method = RequestMethod.POST)
    public String addProductToBasket(Model model, @PathVariable long priceRowId, @ModelAttribute Basket basket) {
        basket.addPriceRow(priceRowService.getPriceRowById(priceRowId));
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
        model.addAttribute("menu", menu);
        return "menu";
    }
}
