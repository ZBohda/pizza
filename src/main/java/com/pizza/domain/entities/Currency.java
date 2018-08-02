package com.pizza.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currency")
@NamedQueries({
        @NamedQuery(name = "Currency.findAll", query = "select cu from Currency cu"),
        @NamedQuery(name = "Currency.findByCode", query = "select cu from Currency cu where cu.code =:code"),
})
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
