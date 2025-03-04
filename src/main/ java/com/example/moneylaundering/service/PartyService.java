package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Party;
import com.example.moneylaundering.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    public List<Party> findAll() throws IOException {
        return partyRepository.findAll();
    }

    public Optional<Party> findById(String partyId) throws IOException {
        return partyRepository.findById(partyId);
    }

    public void save(Party party) throws IOException {
        partyRepository.save(party);
    }

    public void update(Party party) throws IOException {
        partyRepository.update(party);
    }

    public void delete(String partyId) throws IOException {
        partyRepository.delete(partyId);
    }
}
