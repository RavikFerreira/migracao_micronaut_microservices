package com.example.core.dto;

import com.example.core.enums.EEventProductSource;
import com.example.core.enums.EProductStatus;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;

import static io.micronaut.core.util.CollectionUtils.isEmpty;

@Serdeable
public class EventProduct {
    private String id;
    private Product payload;
    @Enumerated(EnumType.STRING)
    private EEventProductSource source;
    @Enumerated(EnumType.STRING)
    private EProductStatus status;
    private List<HistoryProduct> eventHistory;


    public EventProduct(String id, Product payload, EEventProductSource source, EProductStatus status, List<HistoryProduct> eventHistory) {
        this.id = id;
        this.payload = payload;
        this.source = source;
        this.status = status;
        this.eventHistory = eventHistory;
    }

    public EventProduct() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getPayload() {
        return payload;
    }

    public void setPayload(Product payload) {
        this.payload = payload;
    }

    public EEventProductSource getProductSource() {
        return source;
    }

    public void setProductSource(EEventProductSource source) {
        this.source = source;
    }

    public EProductStatus getProductStatus() {
        return status;
    }

    public void setProductStatus(EProductStatus status) {
        this.status = status;
    }

    public List<HistoryProduct> getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(List<HistoryProduct> eventHistory) {
        this.eventHistory = eventHistory;
    }

    public void addToHistory(HistoryProduct history){
        if(isEmpty(eventHistory)){
            eventHistory = new ArrayList<>();
        }
        eventHistory.add(history);
    }

}
