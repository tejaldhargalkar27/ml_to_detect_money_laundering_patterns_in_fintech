package com.example.moneylaundering.service;

import com.example.moneylaundering.model.PartySupplementaryData;
import com.example.moneylaundering.repository.PartySupplementaryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PartySupplementaryDataService {

    @Autowired
    private PartySupplementaryDataRepository partySupplementaryDataRepository;

    public List<PartySupplementaryData> findAll() throws IOException {
        return partySupplementaryDataRepository.findAll();
    }

    public Optional<PartySupplementaryData> findById(String partySupplementaryDataId) throws IOException {
        return partySupplementaryDataRepository.findById(partySupplementaryDataId);
    }

    public void save(PartySupplementaryData data) throws IOException {
        partySupplementaryDataRepository.save(data);
    }

    public void update(PartySupplementaryData data) throws IOException {
        partySupplementaryDataRepository.update(data);
    }

    public void delete(String partySupplementaryDataId) throws IOException {
        partySupplementaryDataRepository.delete(partySupplementaryDataId);
    }
}
