package com.pizza.validators;

import com.pizza.domain.dto.PriceRowFormDTO;
import com.pizza.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PriceRowFormValidator implements Validator {

    @Autowired
    @Qualifier("currencyCodeValidator")
    private CurrencyCodeValidator currencyCodeValidator;

    @Autowired
    @Qualifier("priceValidator")
    private PriceValidator priceValidator;

    @Autowired
    private CurrencyService currencyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return PriceRowFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PriceRowFormDTO priceRowFormDTO = (PriceRowFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.priceRowForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currencyCode", "NotEmpty.priceRowForm.currencyCode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "active", "NotEmpty.priceRowForm.active");

        if (!currencyCodeValidator.valid(priceRowFormDTO.getCurrencyCode())) {
            errors.rejectValue("currencyCode", "Pattern.priceRowForm.code");
        }

        if (currencyService.getCurrencyByCode((priceRowFormDTO.getCurrencyCode())) == null) {
            errors.rejectValue("currencyCode", "NotExists.priceRowForm.code");
        }

        if (!priceValidator.valid(priceRowFormDTO.getPrice())) {
            errors.rejectValue("price", "Pattern.priceRowForm.price");
        }
    }
}
