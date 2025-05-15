package com.example.core.enums;

public enum ETopic {
    START("start"),
    START_PRODUCT("start-product"),
    NOTIFY("notify"),
    ORCHESTRATOR("orchestrator"),
    ORCHESTRATOR_PRODUCT("orchestrator-product"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    PRODUCT_VALIDATION_SUCCESS("product-validation-success"),
    PRODUCT_VALIDATION_FAIL("product-validation-fail"),
    PAYMENT_SUCCESS("payment-success"),
    PAYMENT_FAIL("payment-fail"),
    INVENTORY_SUCCESS("inventory-success"),
    INVENTORY_PRODUCT_SUCCESS("inventory-product-success"),
    INVENTORY_FAIL("inventory-fail");
    private final String topic;

    ETopic(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}
