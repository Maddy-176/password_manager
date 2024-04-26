package com.example.password_manager.demo.password_manager.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.password_manager.demo.password_manager.model.Vault;
import com.example.password_manager.demo.password_manager.repository.VaultRepository;
import com.example.password_manager.demo.password_manager.security.SaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class VaultService {

    private final VaultRepository vaultRepository;

    @Autowired
    public VaultService(VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }
    public void createVault(Scanner scanner){
        System.out.println("Enter vault name:");
        String name= scanner.nextLine();
        System.out.println("Enter vault password:");
        String password= scanner.nextLine();
        Vault vault= new Vault();
        vault.setName(name);
        byte[] salt16Bytes= SaltGenerator.generateSalt();
        var hashedPassword= BCrypt.withDefaults().hash(6,salt16Bytes,password.getBytes(StandardCharsets.UTF_8));
        vault.setMasterPassword(new String(hashedPassword, StandardCharsets.UTF_8));
        Vault savedVault= vaultRepository.save(vault);
        if(savedVault!=null)
            System.out.println("Vault created successfully");
        else
            System.out.println("Vault creation failed");
    }

    public Vault AuthenticateVault(Scanner scanner){
        System.out.println("Enter Vault Name:");
        String vaultName= scanner.nextLine();
        System.out.println("Enter Vault Password:");
        String password= scanner.nextLine();
        Vault vault= vaultRepository.getByName(vaultName);
        var result= BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verify(password.toCharArray(),vault.getMasterPassword().getBytes());
          /*
         if(result.verified) {
             System.out.println("Login Successfull");
             return vault;
         }
         else {
             System.out.println("Login not Successfull");
             return null;
         }

           */
        return vault;


    }
}
