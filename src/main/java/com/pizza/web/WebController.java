package com.pizza.web;


import com.pizza.domain.dto.MenuRowDTO;
import com.pizza.services.CurrencyService;
import com.pizza.services.PriceRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WebController {

    private static final String DEFAULT_CODE = "USD";

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        return "index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenuPage(Model model) {
        List<MenuRowDTO> menu = priceRowService.createMenuRowDTOFromPriceRows(priceRowService.getAllPriceRowsForCurrencyCode(DEFAULT_CODE));
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodesMap());
        model.addAttribute("menu", menu);
        return "menu";
    }
}
