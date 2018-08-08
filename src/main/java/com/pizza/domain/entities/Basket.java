package com.pizza.domain.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("session")
public class Basket implements Serializable {

    private Map<Product, Integer> products = new HashMap<>();


    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            for (PriceRow priceRow : entry.getKey().getPrices()) {
                totalPrice += priceRow.getPrice() * entry.getValue();
            }
        }
        return totalPrice;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        if (products.get(product) > 1) {
            products.put(product, products.get(product) - 1);
        } else {
            products.remove(product);
        }
    }

    public void clear() {
        products.clear();
    }
}
