package com.pizza.validators;

import com.pizza.domain.dto.ProductFormDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProductFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProductFormDTO productFormDTO = (ProductFormDTO)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "NotEmpty.productForm.details");
    }
}
