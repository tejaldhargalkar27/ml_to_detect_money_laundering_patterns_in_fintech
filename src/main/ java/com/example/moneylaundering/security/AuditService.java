package com.example.moneylaundering.security;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class AuditService {

    private static final String AUDIT_LOG_PATH = "src/main/resources/logs/audit.log";

    public void logChange(String action, String data) {
        try (FileWriter writer = new FileWriter(AUDIT_LOG_PATH, true)) {
            writer.write(LocalDateTime.now() + " - " + action + ": " + data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
