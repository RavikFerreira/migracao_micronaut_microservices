package com.tables.config.exceptions;

public class QuantityProductInvalid extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public QuantityProductInvalid(String message) {
        super(message);
    }
}
