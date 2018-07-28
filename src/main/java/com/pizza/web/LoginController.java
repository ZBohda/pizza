package com.pizza.web;

import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.validators.LoginFormValidator;
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
public class LoginController {

    @Autowired
    private LoginFormValidator loginFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginFormValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("loginFormDTO") @Validated LoginFormDTO loginFormDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(loginFormDTO);
            return "login";
        }
        return "result";
    }
}
