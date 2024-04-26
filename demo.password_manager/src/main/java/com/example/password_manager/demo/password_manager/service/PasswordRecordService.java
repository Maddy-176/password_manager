package com.example.password_manager.demo.password_manager.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.password_manager.demo.password_manager.model.PasswordRecord;
import com.example.password_manager.demo.password_manager.model.Vault;
import com.example.password_manager.demo.password_manager.repository.PasswordRecordRepository;
import com.example.password_manager.demo.password_manager.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;
import java.util.UUID;

@Service
public class PasswordRecordService {
    private PasswordRecord passwordRecord;
    @Autowired
    private  PasswordRecordRepository passwordRecordRepository;
    @Autowired
    public PasswordRecordService(){
        passwordRecord=new PasswordRecord();
    }
    public void storePassword(Scanner scanner, boolean isAuthenticated, Vault vault){
        if(!isAuthenticated) {
            System.out.println("Vault is not authenticated. Please authenticate first");
            return;
        }
        System.out.println("Enter service name");
        String serviceName= scanner.nextLine();
        System.out.println("Enter user name");
        String userName= scanner.nextLine();
        System.out.println("Enter the password");
        String password= scanner.nextLine();
        passwordRecord.setPassword(EncryptionUtil.encrypt(password));
        passwordRecord.setServiceName(serviceName);
        passwordRecord.setUserName(userName);
        passwordRecord.setVault(vault);
        PasswordRecord savedRecord=  passwordRecordRepository.save(passwordRecord);
        if(savedRecord!=null)
            System.out.println("Password record saved successfully");
        else
            System.out.println("Failed to save password record");
    }

    public void retrieveRecords(Scanner scanner,boolean isAuthenticated,Vault vault){
        if(!isAuthenticated)
        System.out.println("Vault is not authenticated. Please authenticate first");

        System.out.println("Enter service name");
        String serviceName =  scanner.nextLine();
        PasswordRecord record = passwordRecordRepository.findByServiceNameAndVault(serviceName,vault);
        String password = EncryptionUtil.decrypt(record.getPassword());

        if(record!=null){
            System.out.println("The user name is:"+" "+record.getUserName());
            System.out.println("The password is:"+" "+password);
        }else
            System.out.println("No records found");

    }


}
