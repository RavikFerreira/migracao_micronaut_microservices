package com.example.core.enums;

public enum ETopic {

    START("start"),
    NOTIFY("notify"),
    ORCHESTRATOR("orchestrator"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    PAYMENT_SUCCESS("payment-success"),
    PAYMENT_FAIL("payment-fail"),
    PRODUCT_VALIDATION_SUCCESS("product-validation-success"),
    PRODUCT_VALIDATION_FAIL("product-validation-fail");

    private final String topic;

    ETopic(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}