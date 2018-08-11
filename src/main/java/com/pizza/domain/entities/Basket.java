package com.pizza.domain.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("session")
public class Basket implements Serializable {

    private Map<PriceRow, Integer> products = new HashMap<>();

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

    public void removePriceRow(PriceRow priceRow) {
        if (products.get(priceRow) > 1) {
            products.put(priceRow, products.get(priceRow) - 1);
        } else {
            products.remove(priceRow);
        }
    }

    public void clear() {
        products.clear();
    }
}
