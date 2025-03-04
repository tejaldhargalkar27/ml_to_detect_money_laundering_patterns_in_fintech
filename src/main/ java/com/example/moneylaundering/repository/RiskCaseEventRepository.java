package com.example.moneylaundering.repository;

import com.example.moneylaundering.model.RiskCaseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RiskCaseEventRepository {

    private static final String FILE_PATH = "data/risk_case_event_table.csv";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<RiskCaseEvent> findAll() throws IOException {
        return new CsvToBeanBuilder<RiskCaseEvent>(new FileReader(FILE_PATH))
                .withType(RiskCaseEvent.class)
                .build()
                .parse();
    }

    public Optional<RiskCaseEvent> findById(String riskCaseEventId) throws IOException {
        return findAll().stream()
                .filter(event -> event.getRiskCaseEventId().equals(riskCaseEventId))
                .findFirst();
    }

    public void save(RiskCaseEvent event) throws IOException {
        List<RiskCaseEvent> events = findAll();
        events.add(event);
        writeEventsToFile(events);
    }

    public void update(RiskCaseEvent event) throws IOException {
        List<RiskCaseEvent> events = findAll().stream()
                .map(existingEvent -> existingEvent.getRiskCaseEventId().equals(event.getRiskCaseEventId()) ? event : existingEvent)
                .collect(Collectors.toList());
        writeEventsToFile(events);
    }

    public void delete(String riskCaseEventId) throws IOException {
        List<RiskCaseEvent> events = findAll().stream()
                .filter(event -> !event.getRiskCaseEventId().equals(riskCaseEventId))
                .collect(Collectors.toList());
        writeEventsToFile(events);
    }

    private void writeEventsToFile(List<RiskCaseEvent> events) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, events);
        }
    }
}
