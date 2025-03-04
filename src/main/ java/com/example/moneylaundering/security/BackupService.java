package com.example.moneylaundering.security;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class BackupService {

    private static final String BACKUP_DIR = "src/main/resources/backup/";

    public void backupData(File dataFile) {
        File backupFile = new File(BACKUP_DIR + dataFile.getName() + "_" + LocalDateTime.now());
        try {
            Files.copy(dataFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
