package com.pizza.validators;

import com.pizza.domain.dto.AddressFormDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class AddressFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AddressFormDTO addressFormDTO = (AddressFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.addressForm.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.addressForm.address");
    }
}
