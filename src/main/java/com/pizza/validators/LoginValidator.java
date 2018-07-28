package com.pizza.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("loginValidator")
public class LoginValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String LOGIN_PATTERN = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";

    public LoginValidator() {
        pattern = Pattern.compile(LOGIN_PATTERN);
    }

    public boolean valid(final String login) {

        matcher = pattern.matcher(login);
        return matcher.matches();

    }
}
