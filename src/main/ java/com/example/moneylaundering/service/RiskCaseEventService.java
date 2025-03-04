package com.example.moneylaundering.service;

import com.example.moneylaundering.model.RiskCaseEvent;
import com.example.moneylaundering.repository.RiskCaseEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RiskCaseEventService {

    @Autowired
    private RiskCaseEventRepository riskCaseEventRepository;

    public List<RiskCaseEvent> findAll() throws IOException {
        return riskCaseEventRepository.findAll();
    }

    public Optional<RiskCaseEvent> findById(String riskCaseEventId) throws IOException {
        return riskCaseEventRepository.findById(riskCaseEventId);
    }

    public void save(RiskCaseEvent event) throws IOException {
        riskCaseEventRepository.save(event);
    }

    public void update(RiskCaseEvent event) throws IOException {
        riskCaseEventRepository.update(event);
    }

    public void delete(String riskCaseEventId) throws IOException {
        riskCaseEventRepository.delete(riskCaseEventId);
    }
}
