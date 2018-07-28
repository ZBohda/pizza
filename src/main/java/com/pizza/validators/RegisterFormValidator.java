package com.pizza.validators;

import com.pizza.domain.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterFormValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    private EmailValidator emailValidator;

    @Autowired
    @Qualifier("namesValidator")
    private NamesValidator namesValidator;

    @Autowired
    @Qualifier("loginValidator")
    private LoginValidator loginValidator;

    @Autowired
    @Qualifier("phoneValidator")
    private PhoneValidator phoneValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegisterFormDTO registerFormDTO = (RegisterFormDTO)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty.registerForm.login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registerForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.registerForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.registerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.registerForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.registerForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.registerForm.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.registerForm.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.registerForm.address");

        if(!emailValidator.valid(registerFormDTO.getEmail())){
            errors.rejectValue("email", "Pattern.registerForm.email");
        }

        if (!registerFormDTO.getPassword().equals(registerFormDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.registerForm.confirmPassword");
        }

        if(!namesValidator.valid(registerFormDTO.getFirstName())){
            errors.rejectValue("firstName", "Pattern.registerForm.firstName");
        }

        if(!namesValidator.valid(registerFormDTO.getLastName())){
            errors.rejectValue("lastName", "Pattern.registerForm.lastName");
        }

        if(!loginValidator.valid(registerFormDTO.getLogin())){
            errors.rejectValue("login", "Pattern.registerForm.login");
        }

        if(!phoneValidator.valid(registerFormDTO.getPhone())){
            errors.rejectValue("phone", "Patter.registerForm.phone");
        }

    }
}
