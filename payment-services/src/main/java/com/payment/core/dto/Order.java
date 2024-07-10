package com.payment.core.dto;

import java.io.Serial;
import java.io.Serializable;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Order() {}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
