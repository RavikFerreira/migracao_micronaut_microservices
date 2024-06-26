package com.example.enums;

public enum ETopic {

    START("start"),
    NOTIFY("notify"),
    ORQUESTRATOR("orquestrator"),
    MENU_SUCCESS("menu_success"),
    MENU_FAIL("menu_fail"),
    ORDERS_SUCCESS("orders_success"),
    ORDERS_FAIL("orders_fail");

    private final String topic;

    ETopic(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}
