package com.example.moneylaundering.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class AnonymizationServiceTest {

    private AnonymizationService anonymizationService;

    @BeforeEach
    void setUp() {
        anonymizationService = new AnonymizationService();
    }

    @Test
    void testPseudonymize() throws NoSuchAlgorithmException {
        String data = "Sensitive Data";
        String pseudonymizedData = anonymizationService.pseudonymize(data);
        assertNotNull(pseudonymizedData);
        assertNotEquals(data, pseudonymizedData);
    }
}
