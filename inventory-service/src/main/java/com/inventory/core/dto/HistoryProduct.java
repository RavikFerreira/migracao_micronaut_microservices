package com.inventory.core.dto;

import com.inventory.core.enums.EProductStatus;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
public class HistoryProduct {

    private String source;
    private EProductStatus status;
    private String message;
    private LocalDateTime createdAt;

    public HistoryProduct(String source, EProductStatus status, String message, LocalDateTime createdAt) {
        this.source = source;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
    }

    public HistoryProduct() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
