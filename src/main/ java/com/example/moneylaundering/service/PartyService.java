package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Party;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyService {
    private List<Party> parties = new ArrayList<>();

    public List<Party> getAllParties() {
        return parties;
    }

    public Party createParty(Party party) {
        parties.add(party);
        return party;
    }

    public Party getPartyById(String id) {
        return parties.stream()
                .filter(party -> party.getPartyId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Party updateParty(String id, Party updatedParty) {
        Party party = getPartyById(id);
        if (party != null) {
            party.setName(updatedParty.getName());
            party.setAddress(updatedParty.getAddress());
            party.setPhoneNumber(updatedParty.getPhoneNumber());
            party.setEmail(updatedParty.getEmail());
            party.setRiskCategory(updatedParty.getRiskCategory());
        }
        return party;
    }

    public void deleteParty(String id) {
        parties.removeIf(party -> party.getPartyId().equals(id));
    }
}
