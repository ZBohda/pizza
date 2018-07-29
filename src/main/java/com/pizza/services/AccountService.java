package com.pizza.services;

import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.domain.entities.Account;
import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Customer;
import com.pizza.domain.enums.AccountType;
import com.pizza.repository.AccountRepository;
import com.pizza.repository.AddressRepository;
import com.pizza.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void addNewAccount(RegisterFormDTO registerFormDTO) {
        long customerId = customerRepository.create(createNewCustomerFromRegisterDTO(registerFormDTO));
        Customer customer = customerRepository.read(customerId);
        long accountId = accountRepository.create(createNewAccountFromRegisterDTO(registerFormDTO));
        Account account = accountRepository.read(accountId);
        account.setCustomer(customer);
        accountRepository.update(account);
        Address address = createNewAddressFromRegisterDTO(registerFormDTO, customer);
        addressRepository.create(address);
    }

    private Account createNewAccountFromRegisterDTO(RegisterFormDTO registerFormDTO) {
        Account account = new Account();
        account.setCreationDate(new Date());
        account.setAccountType(AccountType.USER);
        account.setLogin(registerFormDTO.getLogin());
        account.setPassword(registerFormDTO.getPassword());
        account.setEmail(registerFormDTO.getEmail());
        return account;
    }

    private Customer createNewCustomerFromRegisterDTO(RegisterFormDTO registerFormDTO) {
        Customer customer = new Customer();
        customer.setPhone(registerFormDTO.getPhone());
        customer.setFirstName(registerFormDTO.getFirstName());
        customer.setLastName(registerFormDTO.getLastName());
        return customer;
    }

    private Address createNewAddressFromRegisterDTO(RegisterFormDTO registerFormDTO, Customer customer) {
        Address address = new Address();
        address.setCity(registerFormDTO.getCity());
        address.setAddress(registerFormDTO.getAddress());
        address.setCustomer(customer);
        return address;
    }

    public Account findAccountById(long id) {
        return accountRepository.read(id);
    }

    public Account findAccountByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }

    public boolean signIn(LoginFormDTO loginFormDTO, HttpSession session, Model model, BindingResult result) {
        Account account = accountRepository.findAccountByLogin(loginFormDTO.getLogin());
        if (account != null) {
            if (account.getPassword().equals(loginFormDTO.getPassword())) {
                session.setAttribute("userId", account.getCustomer().getId());
                return true;
            } else {
                result.rejectValue("password", "WrongPassword.loginForm.password");
                model.addAttribute(loginFormDTO);
                return false;
            }
        } else {
            result.rejectValue("login", "NoSuchLogin.loginForm.login");
            model.addAttribute(loginFormDTO);
            return false;
        }
    }
}
