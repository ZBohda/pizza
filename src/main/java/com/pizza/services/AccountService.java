package com.pizza.services;

import com.pizza.domain.dto.LoginFormDTO;
import com.pizza.domain.dto.RegisterFormDTO;
import com.pizza.domain.entities.Account;
import com.pizza.domain.entities.Address;
import com.pizza.domain.entities.Customer;
import com.pizza.domain.enums.AccountType;
import com.pizza.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressService addressService;

    public List<Account> findAllCustomersAccount() {
        return accountRepository.findAllCustomerAccounts();
    }

    public void addNewAccount(RegisterFormDTO registerFormDTO) {
        Account account = createNewAccountFromRegisterDTO(registerFormDTO);
        Customer customer = createNewCustomerFromRegisterDTO(registerFormDTO);
        Address address = createNewAddressFromRegisterDTO(registerFormDTO);
        account.setCustomer(customer);
        customer.setAccount(account);
        accountRepository.create(account);
        addressService.addNewAddressesToCustomer(address, customer);
    }

    private Account createNewAccountFromRegisterDTO(RegisterFormDTO registerFormDTO) {
        Account account = new Account();
        account.setCreationDate(new Date());
        account.setAccountType(AccountType.USER);
        account.setLogin(registerFormDTO.getLogin());
        account.setPassword(registerFormDTO.getPassword());
        account.setEmail(registerFormDTO.getEmail());
        account.setStatus(true);
        return account;
    }

    private Customer createNewCustomerFromRegisterDTO(RegisterFormDTO registerFormDTO) {
        Customer customer = new Customer();
        customer.setPhone(registerFormDTO.getPhone());
        customer.setFirstName(registerFormDTO.getFirstName());
        customer.setLastName(registerFormDTO.getLastName());
        return customer;
    }

    private Address createNewAddressFromRegisterDTO(RegisterFormDTO registerFormDTO) {
        Address address = new Address();
        address.setCity(registerFormDTO.getCity());
        address.setAddress(registerFormDTO.getAddress());
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

    public Account findAccountByUserSessionId(long id) {
        return accountRepository.findAccountByUserSessionId(id);
    }

    public void changeAccountStatusToOpposite(long accountId) {
        Account account = accountRepository.read(accountId);
        if (account.isStatus()) {
            account.setStatus(false);
        } else {
            account.setStatus(true);
        }
        accountRepository.update(account);
    }
}
