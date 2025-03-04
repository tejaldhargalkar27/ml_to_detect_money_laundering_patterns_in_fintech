package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.Party;
import com.example.moneylaundering.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public List<Party> getAllParties() throws IOException {
        return partyService.findAll();
    }

    @GetMapping("/{id}")
    public Party getPartyById(@PathVariable String id) throws IOException {
        return partyService.findById(id).orElse(null);
    }

    @PostMapping
    public void createParty(@RequestBody Party party) throws IOException {
        partyService.save(party);
    }

    @PutMapping("/{id}")
    public void updateParty(@PathVariable String id, @RequestBody Party party) throws IOException {
        party.setPartyId(id);
        partyService.update(party);
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable String id) throws IOException {
        partyService.delete(id);
    }
}
