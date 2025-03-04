package com.example.moneylaundering.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionServiceTest {

    private EncryptionService encryptionService;

    @BeforeEach
    void setUp() throws Exception {
        encryptionService = new EncryptionService();
    }

    @Test
    void testEncryptAndDecrypt() throws Exception {
        String data = "Sensitive Data";
        String encryptedData = encryptionService.encrypt(data);
        assertNotNull(encryptedData);

        String decryptedData = encryptionService.decrypt(encryptedData);
        assertEquals(data, decryptedData);
    }
}
