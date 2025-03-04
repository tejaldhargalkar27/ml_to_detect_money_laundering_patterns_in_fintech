package com.example.moneylaundering.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Party {

    @Id
    @CsvBindByName
    private String partyId;
    @CsvBindByName
    private LocalDateTime validityStartTime;
    @CsvBindByName
    private Boolean isEntityDeleted;
    @CsvBindByName
    private String sourceSystem;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private LocalDateTime establishmentDate;
    @CsvBindByName
    private String assetValueRange;
    @CsvBindByName
    private String legalStructure;
    @CsvBindByName
    private String industryClassification;
    @CsvBindByName
    private String riskRating;
    
    // Getters and Setters

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }
}
