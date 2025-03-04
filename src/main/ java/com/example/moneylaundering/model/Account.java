package com.example.moneylaundering.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @CsvBindByName
    private String accountId;
    @CsvBindByName
    private String partyId;
    @CsvBindByName
    private LocalDateTime validityStartTime;
    @CsvBindByName
    private Boolean isEntityDeleted;
    @CsvBindByName
    private String role;
    @CsvBindByName
    private String sourceSystem;

    // Getters and Setters

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isEntityDeleted() {
        return isEntityDeleted;
    }

    public void setEntityDeleted(boolean isEntityDeleted) {
        this.isEntityDeleted = isEntityDeleted;
    }
}
