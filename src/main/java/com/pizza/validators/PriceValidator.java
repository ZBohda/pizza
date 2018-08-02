package com.pizza.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("priceValidator")
public class PriceValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PRICE_PATTERN = "(\\d+\\.\\d{1,2})";

    public PriceValidator() {
        pattern = Pattern.compile(PRICE_PATTERN);
    }

    public boolean valid(final String price) {

        matcher = pattern.matcher(price);
        return matcher.matches();

    }
}
