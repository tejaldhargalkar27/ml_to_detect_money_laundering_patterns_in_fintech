package com.example.moneylaundering.model;

public class AMLResponse {
    private boolean isSuspicious;
    private String riskScore;
    private String message;
    
    // Getters and Setters

    public boolean isSuspicious() {
        return isSuspicious;
    }

    public void setSuspicious(boolean suspicious) {
        isSuspicious = suspicious;
    }

    public String getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(String riskScore) {
        this.riskScore = riskScore;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
