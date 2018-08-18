package com.pizza.domain.entities;

import com.pizza.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("session")
public class Basket implements Serializable {

    private static final String DEFAULT_CURRENCY_CODE = "USD";

    private Map<PriceRow, Integer> products = new HashMap<>();

    private Currency currency = new Currency();

    {
        currency.setCode(DEFAULT_CURRENCY_CODE);
    }

    public Map<PriceRow, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<PriceRow, Integer> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<PriceRow, Integer> entry : products.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public void addPriceRow(PriceRow priceRow) {
        if (products.containsKey(priceRow)) {
            products.put(priceRow, products.get(priceRow) + 1);
        } else {
            products.put(priceRow, 1);
        }
    }

    public void decreasePriceRow(PriceRow priceRow) {
        if (products.get(priceRow) > 1) {
            products.put(priceRow, products.get(priceRow) - 1);
        } else {
            products.remove(priceRow);
        }
    }

    public void removePriceRow(PriceRow priceRow) {
        products.remove(priceRow);
    }

    public void clear() {
        products.clear();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
