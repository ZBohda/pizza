package com.pizza.services;

import com.pizza.domain.entities.Currency;
import com.pizza.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private PriceRowService priceRowService;

    @Autowired
    private ProductService productService;

    public List<Currency> getAll() {
        return currencyRepository.getAll();
    }

    public void addNewCurrency(Currency currency) {
        currencyRepository.create(currency);
    }

    public Currency getCurrencyById(long id) {
        return currencyRepository.read(id);
    }

    public Currency getCurrencyByCode(String code) {
        return currencyRepository.getCurrencyByCode(code);
    }

    public void updateCurrency(Currency currency) {
        currencyRepository.update(currency);
    }

    public void deactivateAllCurrencyPrices(long currencyId) {
        priceRowService.deactivateAllPriceRowsForCurrency(currencyId);
    }
}
