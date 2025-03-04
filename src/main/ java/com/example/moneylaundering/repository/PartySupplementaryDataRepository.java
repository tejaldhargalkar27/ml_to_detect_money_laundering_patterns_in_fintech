package com.example.moneylaundering.repository;

import com.example.moneylaundering.model.PartySupplementaryData;
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
public class PartySupplementaryDataRepository {

    private static final String FILE_PATH = "data/party_supplementary_data.csv";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PartySupplementaryData> findAll() throws IOException {
        return new CsvToBeanBuilder<PartySupplementaryData>(new FileReader(FILE_PATH))
                .withType(PartySupplementaryData.class)
                .build()
                .parse();
    }

    public Optional<PartySupplementaryData> findById(String partySupplementaryDataId) throws IOException {
        return findAll().stream()
                .filter(data -> data.getPartySupplementaryDataId().equals(partySupplementaryDataId))
                .findFirst();
    }

    public void save(PartySupplementaryData data) throws IOException {
        List<PartySupplementaryData> supplementaryDataList = findAll();
        supplementaryDataList.add(data);
        writeDataToFile(supplementaryDataList);
    }

    public void update(PartySupplementaryData data) throws IOException {
        List<PartySupplementaryData> supplementaryDataList = findAll().stream()
                .map(existingData -> existingData.getPartySupplementaryDataId().equals(data.getPartySupplementaryDataId()) ? data : existingData)
                .collect(Collectors.toList());
        writeDataToFile(supplementaryDataList);
    }

    public void delete(String partySupplementaryDataId) throws IOException {
        List<PartySupplementaryData> supplementaryDataList = findAll().stream()
                .filter(data -> !data.getPartySupplementaryDataId().equals(partySupplementaryDataId))
                .collect(Collectors.toList());
        writeDataToFile(supplementaryDataList);
    }

    private void writeDataToFile(List<PartySupplementaryData> data) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, data);
        }
    }
}
