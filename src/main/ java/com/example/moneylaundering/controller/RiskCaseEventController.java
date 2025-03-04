package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.RiskCaseEvent;
import com.example.moneylaundering.service.RiskCaseEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/risk-case-events")
public class RiskCaseEventController {

    @Autowired
    private RiskCaseEventService riskCaseEventService;

    @GetMapping
    public List<RiskCaseEvent> getAllRiskCaseEvents() throws IOException {
        return riskCaseEventService.findAll();
    }

    @GetMapping("/{id}")
    public RiskCaseEvent getRiskCaseEventById(@PathVariable String id) throws IOException {
        return riskCaseEventService.findById(id).orElse(null);
    }

    @PostMapping
    public void createRiskCaseEvent(@RequestBody RiskCaseEvent event) throws IOException {
        riskCaseEventService.save(event);
    }

    @PutMapping("/{id}")
    public void updateRiskCaseEvent(@PathVariable String id, @RequestBody RiskCaseEvent event) throws IOException {
        event.setRiskCaseEventId(id);
        riskCaseEventService.update(event);
    }

    @DeleteMapping("/{id}")
    public void deleteRiskCaseEvent(@PathVariable String id) throws IOException {
        riskCaseEventService.delete(id);
    }
}
