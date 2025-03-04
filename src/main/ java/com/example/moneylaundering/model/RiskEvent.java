package com.example.moneylaundering.model;

import java.time.LocalDateTime;

public class RiskEvent {
    private String riskEventId;
    private String type;
    private LocalDateTime eventTime;
    private String description;
    
    // Getters and Setters

    public String getRiskEventId() {
        return riskEventId;
    }

    public void setRiskEventId(String riskEventId) {
        this.riskEventId = riskEventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
