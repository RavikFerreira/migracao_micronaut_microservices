package com.payment.core.dto;

import com.payment.core.enums.EStatus;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.micronaut.core.util.CollectionUtils.isEmpty;

@Serdeable
public class Event {

    private String id;
    private String transactionId;
    private String tableId;
    private TableBar payload;
    private String source;
    private EStatus status;
    private List<History> eventHistory;
    private LocalDateTime createdAt;

    public Event(String id, String transactionId, String tableId, TableBar payload, String source, EStatus status, List<History> eventHistory, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.tableId = tableId;
        this.payload = payload;
        this.source = source;
        this.status = status;
        this.eventHistory = eventHistory;
        this.createdAt = createdAt;
    }

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public TableBar getPayload() {
        return payload;
    }

    public void setPayload(TableBar payload) {
        this.payload = payload;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public List<History> getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(List<History> eventHistory) {
        this.eventHistory = eventHistory;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void addToHistory(History history){
        if(isEmpty(eventHistory)){
            eventHistory = new ArrayList<>();
        }
        eventHistory.add(history);
    }
}
