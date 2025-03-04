package com.example.moneylaundering.controller;

import com.example.moneylaundering.model.PartySupplementaryData;
import com.example.moneylaundering.service.PartySupplementaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/party-supplementary-data")
public class PartySupplementaryDataController {

    @Autowired
    private PartySupplementaryDataService partySupplementaryDataService;

    @GetMapping
    public List<PartySupplementaryData> getAllPartySupplementaryData() throws IOException {
        return partySupplementaryDataService.findAll();
    }

    @GetMapping("/{id}")
    public PartySupplementaryData getPartySupplementaryDataById(@PathVariable String id) throws IOException {
        return partySupplementaryDataService.findById(id).orElse(null);
    }

    @PostMapping
    public void createPartySupplementaryData(@RequestBody PartySupplementaryData data) throws IOException {
        partySupplementaryDataService.save(data);
    }

    @PutMapping("/{id}")
    public void updatePartySupplementaryData(@PathVariable String id, @RequestBody PartySupplementaryData data) throws IOException {
        data.setPartySupplementaryDataId(id);
        partySupplementaryDataService.update(data);
    }

    @DeleteMapping("/{id}")
    public void deletePartySupplementaryData(@PathVariable String id) throws IOException {
        partySupplementaryDataService.delete(id);
    }
}
