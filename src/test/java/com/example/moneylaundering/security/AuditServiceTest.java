package com.example.moneylaundering.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    void setUp() {
        auditService = new AuditService();
    }

    @Test
    void testLogChange() throws IOException {
        String action = "UPDATE";
        String data = "Sample Data";
        auditService.logChange(action, data);

        File logFile = new File("src/main/resources/logs/audit.log");
        assertTrue(logFile.exists());

        String logContent = new String(Files.readAllBytes(logFile.toPath()));
        assertTrue(logContent.contains(action));
        assertTrue(logContent.contains(data));
    }
}
