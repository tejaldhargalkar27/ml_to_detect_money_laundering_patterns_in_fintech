package com.example.moneylaundering.service;

import com.example.moneylaundering.model.RiskEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RiskEventServiceTest {

    private RiskEventService riskEventService;

    @BeforeEach
    void setUp() {
        riskEventService = new RiskEventService();
    }

    @Test
    void testCreateRiskEvent() {
        RiskEvent riskEvent = new RiskEvent();
        riskEvent.setRiskEventId("1");
        riskEvent.setType("Fraud");
        riskEvent.setEventTime(LocalDateTime.now());
        riskEvent.setDescription("Suspicious transaction detected");

        RiskEvent createdRiskEvent = riskEventService.createRiskEvent(riskEvent);
        assertNotNull(createdRiskEvent);
        assertEquals("1", createdRiskEvent.getRiskEventId());
    }

    @Test
    void testGetRiskEventById() {
        RiskEvent riskEvent = new RiskEvent();
        riskEvent.setRiskEventId("1");
        riskEvent.setType("Fraud");
        riskEvent.setEventTime(LocalDateTime.now());
        riskEvent.setDescription("Suspicious transaction detected");

        riskEventService.createRiskEvent(riskEvent);
        RiskEvent retrievedRiskEvent = riskEventService.getRiskEventById("1");
        assertNotNull(retrievedRiskEvent);
        assertEquals("1", retrievedRiskEvent.getRiskEventId());
    }

    @Test
    void testUpdateRiskEvent() {
        RiskEvent riskEvent = new RiskEvent();
        riskEvent.setRiskEventId("1");
        riskEvent.setType("Fraud");
        riskEvent.setEventTime(LocalDateTime.now());
        riskEvent.setDescription("Suspicious transaction detected");

        riskEventService.createRiskEvent(riskEvent);
        riskEvent.setDescription("Updated suspicious transaction details");
        RiskEvent updatedRiskEvent = riskEventService.updateRiskEvent("1", riskEvent);
        assertNotNull(updatedRiskEvent);
        assertEquals("Updated suspicious transaction details", updatedRiskEvent.getDescription());
    }

    @Test
    void testDeleteRiskEvent() {
        RiskEvent riskEvent = new RiskEvent();
        riskEvent.setRiskEventId("1");
        riskEvent.setType("Fraud");
        riskEvent.setEventTime(LocalDateTime.now());
        riskEvent.setDescription("Suspicious transaction detected");

        riskEventService.createRiskEvent(riskEvent);
        riskEventService.deleteRiskEvent("1");
        assertNull(riskEventService.getRiskEventById("1"));
    }
}
