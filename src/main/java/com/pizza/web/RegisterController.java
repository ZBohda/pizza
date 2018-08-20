package com.pizza.web;

import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.services.AccountService;
import com.pizza.services.CurrencyService;
import com.pizza.validators.RegisterFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterFormValidator registerFormValidator;

    @Autowired
    private CurrencyService currencyService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registerFormValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(Model model) {
        model.addAttribute("registerFormDTO", new RegisterFormDTO());
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("registerFormDTO") @Validated RegisterFormDTO registerFormDTO, BindingResult result, Model model) {
        model.addAttribute("currencyCodes", currencyService.getCurrencyIdsCodesMap());
        if (result.hasErrors()) {
            model.addAttribute(registerFormDTO);
            return "register";
        } else {
            accountService.addNewAccount(registerFormDTO);
            return "index";
        }
    }
}
