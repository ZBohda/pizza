package com.pizza.services;

import com.pizza.domain.dto.CurrencyFormDTO;
import com.pizza.domain.entities.Currency;
import com.pizza.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAll(){
        return currencyRepository.getAll();
    }

    public void addNewCurrency(CurrencyFormDTO currencyFormDTO){
        Currency currency = new Currency();
        currency.setCode(currencyFormDTO.getCode());
        currency.setName(currencyFormDTO.getName());
        currencyRepository.create(currency);
    }
}
