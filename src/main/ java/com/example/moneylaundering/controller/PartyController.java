package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.Party;
import com.example.moneylaundering.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public List<Party> getAllParties() {
        return partyService.getAllParties();
    }

    @PostMapping
    public Party createParty(@RequestBody Party party) {
        return partyService.createParty(party);
    }

    @GetMapping("/{id}")
    public Party getPartyById(@PathVariable String id) {
        return partyService.getPartyById(id);
    }

    @PutMapping("/{id}")
    public Party updateParty(@PathVariable String id, @RequestBody Party party) {
        return partyService.updateParty(id, party);
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable String id) {
        partyService.deleteParty(id);
    }
}
