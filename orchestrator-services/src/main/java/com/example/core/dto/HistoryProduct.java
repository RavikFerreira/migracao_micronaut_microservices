package com.example.core.dto;

import com.example.core.enums.EEventProductSource;
import com.example.core.enums.EProductStatus;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
public class HistoryProduct {

    private EEventProductSource source;
    private EProductStatus status;
    private String message;
    private LocalDateTime createdAt;

    public HistoryProduct(EEventProductSource source, EProductStatus status, String message, LocalDateTime createdAt) {
        this.source = source;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
    }

    public HistoryProduct() {
    }

    public EEventProductSource getSource() {
        return source;
    }

    public void setSource(EEventProductSource source) {
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
