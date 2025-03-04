package com.example.moneylaundering.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private SecretKey secretKey;

    public EncryptionService() throws Exception {
        this.secretKey = loadKey();
    }

    private SecretKey loadKey() throws Exception {
        byte[] keyBytes = getClass().getClassLoader().getResourceAsStream("encryption-keys/aes-key.key").readAllBytes();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
