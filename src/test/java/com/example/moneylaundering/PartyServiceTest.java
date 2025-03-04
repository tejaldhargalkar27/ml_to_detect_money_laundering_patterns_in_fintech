package com.example.moneylaundering.service;

import com.example.moneylaundering.model.Party;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyServiceTest {

    private PartyService partyService;

    @BeforeEach
    void setUp() {
        partyService = new PartyService();
    }

    @Test
    void testCreateParty() {
        Party party = new Party();
        party.setPartyId("1");
        party.setName("John Doe");
        party.setAddress("123 Main St");
        party.setPhoneNumber("1234567890");
        party.setEmail("john@example.com");
        party.setRiskCategory("Low");

        Party createdParty = partyService.createParty(party);
        assertNotNull(createdParty);
        assertEquals("1", createdParty.getPartyId());
    }

    @Test
    void testGetPartyById() {
        Party party = new Party();
        party.setPartyId("1");
        party.setName("John Doe");
        party.setAddress("123 Main St");
        party.setPhoneNumber("1234567890");
        party.setEmail("john@example.com");
        party.setRiskCategory("Low");

        partyService.createParty(party);
        Party retrievedParty = partyService.getPartyById("1");
        assertNotNull(retrievedParty);
        assertEquals("1", retrievedParty.getPartyId());
    }

    @Test
    void testUpdateParty() {
        Party party = new Party();
        party.setPartyId("1");
        party.setName("John Doe");
        party.setAddress("123 Main St");
        party.setPhoneNumber("1234567890");
        party.setEmail("john@example.com");
        party.setRiskCategory("Low");

        partyService.createParty(party);
        party.setRiskCategory("High");
        Party updatedParty = partyService.updateParty("1", party);
        assertNotNull(updatedParty);
        assertEquals("High", updatedParty.getRiskCategory());
    }

    @Test
    void testDeleteParty() {
        Party party = new Party();
        party.setPartyId("1");
        party.setName("John Doe");
        party.setAddress("123 Main St");
        party.setPhoneNumber("1234567890");
        party.setEmail("john@example.com");
        party.setRiskCategory("Low");

        partyService.createParty(party);
        partyService.deleteParty("1");
        assertNull(partyService.getPartyById("1"));
    }
}
