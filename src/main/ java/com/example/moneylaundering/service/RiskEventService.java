package com.example.moneylaundering.service;

import com.example.moneylaundering.model.RiskEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskEventService {
    private List<RiskEvent> riskEvents = new ArrayList<>();

    public List<RiskEvent> getAllRiskEvents() {
        return riskEvents;
    }

    public RiskEvent createRiskEvent(RiskEvent riskEvent) {
        riskEvents.add(riskEvent);
        return riskEvent;
    }

    public RiskEvent getRiskEventById(String id) {
        return riskEvents.stream()
                .filter(riskEvent -> riskEvent.getRiskEventId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public RiskEvent updateRiskEvent(String id, RiskEvent updatedRiskEvent) {
        RiskEvent riskEvent = getRiskEventById(id);
        if (riskEvent != null) {
            riskEvent.setType(updatedRiskEvent.getType());
            riskEvent.setEventTime(updatedRiskEvent.getEventTime());
            riskEvent.setDescription(updatedRiskEvent.getDescription());
        }
        return riskEvent;
    }

    public void deleteRiskEvent(String id) {
        riskEvents.removeIf(riskEvent -> riskEvent.getRiskEventId().equals(id));
    }
}
