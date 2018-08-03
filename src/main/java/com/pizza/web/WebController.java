package com.pizza.web;

import com.pizza.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(){
        return "index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenuPage(){
        return "menu";
    }
}
