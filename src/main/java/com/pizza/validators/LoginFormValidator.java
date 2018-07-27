package com.pizza.validators;

import com.pizza.domain.dto.LoginFormDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LoginFormDTO loginFormDTO = (LoginFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty.loginForm.login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.loginForm.password");

    }
}
