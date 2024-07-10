package com.tables.core.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;

@Serdeable
@AllArgsConstructor
public class EventFilters {

    private String tableId;
    private String transactionId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
