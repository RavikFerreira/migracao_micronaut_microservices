package com.payment.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;


public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    private String id;
    private String idProduct;
    private String name;
    private Double price = 0.0;

    public Product(String id,String idProduct, String name, Double price) {
        this.id = id;
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
    }
    public Product(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
