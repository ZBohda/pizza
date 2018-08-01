package com.pizza.validators;

import com.pizza.domain.dto.CurrencyFormDTO;
import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.domain.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CurrencyFormValidator implements Validator {

    @Autowired
    @Qualifier("namesValidator")
    private NamesValidator namesValidator;

    @Autowired
    @Qualifier("currencyCodeValidator")
    private CurrencyCodeValidator currencyCodeValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return CurrencyFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CurrencyFormDTO currencyFormDTO = (CurrencyFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.currencyForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.currencyForm.code");

        if (!namesValidator.valid(currencyFormDTO.getName())) {
            errors.rejectValue("name", "Pattern.currencyForm.name");
        }

        if (!currencyCodeValidator.valid(currencyFormDTO.getCode())) {
            errors.rejectValue("code", "Pattern.currencyForm.code");
        }
    }
}
