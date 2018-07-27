package com.pizza.services;

import com.pizza.domain.entities.Account;
import com.pizza.domain.enums.AccountType;
import com.pizza.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public long addNewAccount(Account account) {
        account.setCreationDate(new Date());
        account.setAccountType(AccountType.USER);
        return accountRepository.create(account);
    }

    public Account findAccountById(long id) {
        return accountRepository.read(id);
    }
}
