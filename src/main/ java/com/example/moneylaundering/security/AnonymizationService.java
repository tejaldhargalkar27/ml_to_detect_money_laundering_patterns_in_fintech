package com.example.moneylaundering.security;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AnonymizationService {

    public String pseudonymize(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(data.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
