package com.pizza.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price_row")
@NamedQueries({
        @NamedQuery(name = "PriceRow.findAllPricesForProductId", query = "select pr from PriceRow pr where pr.product.id =:product"),
        @NamedQuery(name = "PriceRow.findAllPricesForCurrencyId", query = "select pr from PriceRow pr where pr.currency.id =:currency"),
        @NamedQuery(name = "PriceRow.findAllActivePriceRowsForCurrencyCode", query = "select pr from PriceRow pr where pr.currency.code =:code and pr.active = true"),
        @NamedQuery(name = "PriceRow.findPriceRowForNewCurrency", query = "select pr from PriceRow pr where pr.product =:product and pr.currency =:currency"),

})
public class PriceRow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "active")
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRow)) return false;

        PriceRow priceRow = (PriceRow) o;

        if (id != priceRow.id) return false;
        return active == priceRow.active;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
