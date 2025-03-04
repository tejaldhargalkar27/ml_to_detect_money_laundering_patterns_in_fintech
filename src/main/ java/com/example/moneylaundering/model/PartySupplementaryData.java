package com.example.moneylaundering.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PartySupplementaryData {

    @Id
    @CsvBindByName
    private String partySupplementaryDataId;
    @CsvBindByName
    private LocalDateTime validityStartTime;
    @CsvBindByName
    private Boolean isEntityDeleted;
    @CsvBindByName
    private String sourceSystem;
    @CsvBindByName
    private String partyId;
    @CsvBindByName
    private Integer creditScore;
    @CsvBindByName
    private String riskFactors;
    @CsvBindByName
    private String regulatoryIdentifiers;
    @CsvBindByName
    private String incomeLevel;

    // Getters and Setters
}
