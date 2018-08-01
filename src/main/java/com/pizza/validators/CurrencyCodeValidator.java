package com.pizza.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("currencyCodeValidator")
public class CurrencyCodeValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String CODE_PATTERN = "^[A-Z]{3}$";

    public CurrencyCodeValidator() {
        pattern = Pattern.compile(CODE_PATTERN);
    }

    public boolean valid(final String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
