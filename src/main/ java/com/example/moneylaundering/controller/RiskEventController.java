package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.RiskEvent;
import com.example.moneylaundering.service.RiskEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/risk-events")
public class RiskEventController {

    @Autowired
    private RiskEventService riskEventService;

    @GetMapping
    public List<RiskEvent> getAllRiskEvents() {
        return riskEventService.getAllRiskEvents();
    }

    @PostMapping
    public RiskEvent createRiskEvent(@RequestBody RiskEvent riskEvent) {
        return riskEventService.createRiskEvent(riskEvent);
    }

    @GetMapping("/{id}")
    public RiskEvent getRiskEventById(@PathVariable String id) {
        return riskEventService.getRiskEventById(id);
    }

    @PutMapping("/{id}")
    public RiskEvent updateRiskEvent(@PathVariable String id, @RequestBody RiskEvent riskEvent) {
        return riskEventService.updateRiskEvent(id, riskEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteRiskEvent(@PathVariable String id) {
        riskEventService.deleteRiskEvent(id);
    }
}
