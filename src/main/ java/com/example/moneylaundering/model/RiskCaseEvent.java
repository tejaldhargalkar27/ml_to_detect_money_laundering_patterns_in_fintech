package com.example.moneylaundering.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class RiskCaseEvent {

    @Id
    @CsvBindByName
    private String riskCaseEventId;
    @CsvBindByName
    private LocalDateTime eventTime;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String partyId;
    @CsvBindByName
    private String riskTypologyMeasurements;
    @CsvBindByName
    private String riskCaseId;
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
