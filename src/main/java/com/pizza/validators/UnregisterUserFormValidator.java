package com.pizza.validators;

import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.domain.dto.UnregisteredUserFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UnregisterUserFormValidator implements Validator {

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
        return UnregisterUserFormValidator.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UnregisteredUserFormDTO unregisteredUserFormDTO = (UnregisteredUserFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.registerForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.registerForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.registerForm.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.registerForm.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.registerForm.address");

        if(!namesValidator.valid(unregisteredUserFormDTO.getFirstName())){
            errors.rejectValue("firstName", "Pattern.registerForm.firstName");
        }

        if(!namesValidator.valid(unregisteredUserFormDTO.getLastName())){
            errors.rejectValue("lastName", "Pattern.registerForm.lastName");
        }

        if(!phoneValidator.valid(unregisteredUserFormDTO.getPhone())){
            errors.rejectValue("phone", "Patter.registerForm.phone");
        }
    }
}
