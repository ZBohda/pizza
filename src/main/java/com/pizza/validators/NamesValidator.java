package com.pizza.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("namesValidator")
public class NamesValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String NAMES_PATTERN = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";

    public NamesValidator () {
        pattern = Pattern.compile(NAMES_PATTERN);
    }

    public boolean valid(final String name) {

        matcher = pattern.matcher(name);
        return matcher.matches();

    }
}
