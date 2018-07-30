package com.pizza.web;

import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.domain.enums.AccountType;
import com.pizza.services.AccountService;
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

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

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
    public String signIn(@ModelAttribute("loginFormDTO") @Validated LoginFormDTO loginFormDTO, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute(loginFormDTO);
            return "login";
        }
        if (accountService.signIn(loginFormDTO, session, model, result)) {
            if (accountService.findAccountByUserSessionId(Long.parseLong(session.getAttribute("userId").toString())).getAccountType().equals(AccountType.ADMIN)) {
                return "admin-index";
            } return "index";
        } return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";
    }
}
