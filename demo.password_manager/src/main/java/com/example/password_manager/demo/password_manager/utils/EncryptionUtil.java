package com.example.password_manager.demo.password_manager.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

public class EncryptionUtil {

    private static final String ALGORITHM= "AES";
    private static final String PADDING = "AES/ECB/PKCS5Padding";
    private static String ENCRYPTION_KEY= "A@SD1FPO#I_O!U34)";
    public static String encrypt(String value){
        try{
            byte[] keyBytes = new byte[16]; // 128-bit key length
            System.arraycopy(ENCRYPTION_KEY.getBytes("UTF-8"), 0, keyBytes, 0, Math.min(ENCRYPTION_KEY.getBytes("UTF-8").length, keyBytes.length));
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher= Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(encryptedByteValue);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String value){
        try{
            byte[] keyBytes = new byte[16]; // 128-bit key length
            System.arraycopy(ENCRYPTION_KEY.getBytes("UTF-8"), 0, keyBytes, 0, Math.min(ENCRYPTION_KEY.getBytes("UTF-8").length, keyBytes.length));
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] decryptedValue64= Base64.getDecoder().decode(value);
            byte[] decryptedByteValue= cipher.doFinal(decryptedValue64);
            return new String(decryptedByteValue,"utf-8");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
