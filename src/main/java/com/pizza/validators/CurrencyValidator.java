package com.pizza.validators;

import com.pizza.domain.entities.Currency;
import com.pizza.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CurrencyValidator implements Validator {

    @Autowired
    @Qualifier("currencyCodeValidator")
    private CurrencyCodeValidator currencyCodeValidator;

    @Autowired
    private CurrencyService currencyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Currency.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Currency currency = (Currency) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.currencyForm.code");

        if (!currencyCodeValidator.valid(currency.getCode())) {
            errors.rejectValue("code", "Pattern.currencyForm.code");
        }

        if(currencyService.getCurrencyByCode(currency.getCode()) != null){
            errors.rejectValue("code", "IsExists.currencyForm.code");
        }
    }
}
