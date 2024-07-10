package com.example.core.dto;

import com.example.core.enums.EEventSource;
import com.example.core.enums.EStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class History {

    private EEventSource source;
    private EStatus status;
    private String message;
    private LocalDateTime createdAt;

    public History(EEventSource source, EStatus status, String message, LocalDateTime createdAt) {
        this.source = source;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
    }

    public History() {
    }

    public EEventSource getSource() {
        return source;
    }

    public void setSource(EEventSource source) {
        this.source = source;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
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
