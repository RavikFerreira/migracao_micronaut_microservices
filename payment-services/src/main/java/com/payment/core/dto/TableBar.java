package com.payment.core.dto;
import com.payment.core.models.enums.State;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


public class TableBar implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String idTable;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> products;

    private double account = 0.0;
    @Enumerated(EnumType.STRING)
    private State state;

    public TableBar(String id, String idTable, List<Order> products, double account, State state) {
        this.id = id;
        this.idTable = idTable;
        this.products = products;
        this.account = account;
        this.state = state;

    }
    public TableBar(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public List<Order> getProducts() {
        return products;
    }

    public void setProducts(List<Order> products) {
        this.products = products;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

