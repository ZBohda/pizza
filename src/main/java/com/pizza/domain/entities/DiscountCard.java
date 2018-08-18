package com.pizza.domain.entities;

import com.pizza.domain.enums.DiscountCardState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discount_card")
public class DiscountCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "discount_points")
    private int discountPoints;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private DiscountCardState discountCardState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiscountPoints() {
        return discountPoints;
    }

    public void setDiscountPoints(int discountPoints) {
        this.discountPoints = discountPoints;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DiscountCardState getDiscountCardState() {
        return discountCardState;
    }

    public void setDiscountCardState(DiscountCardState discountCardState) {
        this.discountCardState = discountCardState;
    }
}
