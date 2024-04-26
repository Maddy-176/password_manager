package com.example.password_manager.demo.password_manager.security;

import java.security.SecureRandom;

public class SaltGenerator {

    public static byte[] generateSalt(){
        SecureRandom random= new SecureRandom();
        byte[] salt= new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String bytesToHex(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for(byte b:bytes){
            String hex= Integer.toHexString(0Xff & b);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        if(hexString.length()>10){
            return hexString.substring(0,10);
        }else if(hexString.length()<10){
            int paddingLength= 10-hexString.length();
            StringBuilder paddedString= new StringBuilder();
            for(int i=0;i<paddingLength;i++){
                paddedString.append('0');
            }
            paddedString.append(hexString);
            return paddedString.toString();

        }
        return hexString.toString();
    }
}
