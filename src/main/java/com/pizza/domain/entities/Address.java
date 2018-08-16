package com.pizza.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "select add from Address add"),
        @NamedQuery(name = "Address.findAddressById", query = "select add from Address add where add.id =:id"),
        @NamedQuery(name = "Address.findAllAddressesForCustomerId", query = "select add from Address add where add.customer.id =:customerId"),
        @NamedQuery(name = "Address.findAddressByCustomerId", query = "select add from Address add where add.customer.id =:customerId")
})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "address_customer_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
