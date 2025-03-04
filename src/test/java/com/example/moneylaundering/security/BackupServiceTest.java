package com.example.moneylaundering.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BackupServiceTest {

    private BackupService backupService;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        backupService = new BackupService();
        testFile = new File("src/test/resources/testFile.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Test Data");
        }
    }

    @Test
    void testBackupData() {
        backupService.backupData(testFile);
        File backupDir = new File("src/main/resources/backup/");
        assertTrue(backupDir.listFiles().length > 0);
    }
}
