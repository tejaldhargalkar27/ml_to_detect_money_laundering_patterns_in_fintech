package com.example.moneylaundering.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @CsvBindByName
    private String transactionId;
    @CsvBindByName
    private LocalDateTime validityStartTime;
    @CsvBindByName
    private Boolean isEntityDeleted;
    @CsvBindByName
    private String sourceSystem;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String direction;
    @CsvBindByName
    private String accountId;
    @CsvBindByName
    private String counterpartyAccount;
    @CsvBindByName
    private LocalDateTime bookTime;
    @CsvBindByName
    private Integer normalizedBookedAmount;
    @CsvBindByName
    private String geographicLocation;
    
    // Getters and Setters

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
