package com.pizza.services;

import com.pizza.domain.entities.Currency;
import com.pizza.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private PriceRowService priceRowService;

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

    public Map<Long, String> getCurrencyIdsCodesMap(){
        List<Currency> currencies = currencyRepository.getAll();
        Map<Long, String> currencyCodes = new HashMap<>();
        for(Currency currency : currencies){
            currencyCodes.put(currency.getId(), currency.getCode());
        }
        return currencyCodes;
    }

    public Map<String, String> getCurrencyCodesMap(){
        List<Currency> currencies = currencyRepository.getAll();
        Map<String, String> currencyCodes = new HashMap<>();
        for(Currency currency : currencies){
            currencyCodes.put(currency.getCode(), currency.getCode());
        }
        return currencyCodes;
    }

    public void deactivateAllCurrencyPrices(long currencyId) {
        priceRowService.deactivateAllPriceRowsForCurrency(currencyId);
    }
}
