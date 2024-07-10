package com.payment.core.enums;

public enum ETopic {

    START("start"),
    NOTIFY("notify"),
    ORQUESTRATOR("orquestrator"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    PAYMENT_SUCCESS("payment-success"),
    PAYMENT_FAIL("payment-fail");

    private final String topic;

    ETopic(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}
