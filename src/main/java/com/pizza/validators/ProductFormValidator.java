package com.pizza.validators;

import com.pizza.domain.dto.ProductFormDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class ProductFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductFormDTO.class.equals(aClass);
    }

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg");

    @Override
    public void validate(Object target, Errors errors) {


        ProductFormDTO productFormDTO = (ProductFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "NotEmpty.productForm.details");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "NotEmpty.productForm.file");

        if(!CONTENT_TYPES.contains(productFormDTO.getFile().getContentType())){
            errors.rejectValue("file", "Pattern.registerForm.file");
        }
    }
}
