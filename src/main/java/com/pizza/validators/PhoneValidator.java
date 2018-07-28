package com.pizza.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("phoneValidator")
public class PhoneValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PHONE_PATTERN = "^\\+?(?:[0-9]?){6,14}[0-9]$";

    public PhoneValidator() {
        pattern = Pattern.compile(PHONE_PATTERN);
    }

    public boolean valid(final String phone) {

        matcher = pattern.matcher(phone);
        return matcher.matches();

    }
}

