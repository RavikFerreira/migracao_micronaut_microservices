package com.inventory.core.dto;

import com.inventory.core.enums.EProductStatus;
import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.List;

import static io.micronaut.core.util.CollectionUtils.isEmpty;

@Serdeable
public class EventProduct {

    private String id;
    private Product payload;
    private String source;
    private EProductStatus status;
    private List<HistoryProduct> eventHistory;


    public EventProduct(String id, Product payload, String source, EProductStatus status, List<HistoryProduct> eventHistory) {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public EProductStatus getStatus() {
        return status;
    }

    public void setStatus(EProductStatus status) {
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
