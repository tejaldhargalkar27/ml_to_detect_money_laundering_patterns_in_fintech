package com.example.moneylaundering.model;

public class Account {
    private String accountId;
    private String partyId;
    private String accountType;
    private double balance;
    private boolean isEntityDeleted;
    
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
