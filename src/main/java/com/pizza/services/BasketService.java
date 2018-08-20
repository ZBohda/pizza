package com.pizza.services;

import com.pizza.domain.entities.Basket;
import com.pizza.domain.entities.Currency;
import com.pizza.domain.entities.PriceRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    @Autowired
    private PriceRowService priceRowService;

    public Basket recalculateBasketProductsForNewCurrency(Basket basket, Currency currency) {
        basket.setCurrency(currency);
        recalculateProductsForNewCurrency(basket, currency);
        return basket;
    }

    private void recalculateProductsForNewCurrency(Basket basket, Currency currency) {
        Map<PriceRow, Integer> recalculatedProducts = new HashMap<>();
        for (Map.Entry<PriceRow, Integer> entry : basket.getProducts().entrySet()) {
            PriceRow priceRow = priceRowService.findPriceRowForNewCurrency(currency, entry.getKey().getProduct());
            recalculatedProducts.put(priceRow, entry.getValue());
        }
        basket.clear();
        basket.setProducts(recalculatedProducts);
    }
}
