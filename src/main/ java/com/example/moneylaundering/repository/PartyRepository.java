package com.example.moneylaundering.repository;

import com.example.moneylaundering.model.Party;
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
public class PartyRepository {

    private static final String FILE_PATH = "data/party_table.csv";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Party> findAll() throws IOException {
        return new CsvToBeanBuilder<Party>(new FileReader(FILE_PATH))
                .withType(Party.class)
                .build()
                .parse();
    }

    public Optional<Party> findById(String partyId) throws IOException {
        return findAll().stream()
                .filter(party -> party.getPartyId().equals(partyId))
                .findFirst();
    }

    public void save(Party party) throws IOException {
        List<Party> parties = findAll();
        parties.add(party);
        writePartiesToFile(parties);
    }

    public void update(Party party) throws IOException {
        List<Party> parties = findAll().stream()
                .map(existingParty -> existingParty.getPartyId().equals(party.getPartyId()) ? party : existingParty)
                .collect(Collectors.toList());
        writePartiesToFile(parties);
    }

    public void delete(String partyId) throws IOException {
        List<Party> parties = findAll().stream()
                .filter(party -> !party.getPartyId().equals(partyId))
                .collect(Collectors.toList());
        writePartiesToFile(parties);
    }

    private void writePartiesToFile(List<Party> parties) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            objectMapper.writeValue(writer, parties);
        }
    }
}
