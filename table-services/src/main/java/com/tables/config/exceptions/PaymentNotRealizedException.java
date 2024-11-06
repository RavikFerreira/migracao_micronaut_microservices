package com.tables.config.exceptions;

public class PaymentNotRealizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PaymentNotRealizedException(String message) {
        super(message);
    }
}