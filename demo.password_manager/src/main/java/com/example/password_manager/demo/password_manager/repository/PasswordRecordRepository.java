package com.example.password_manager.demo.password_manager.repository;

import com.example.password_manager.demo.password_manager.model.PasswordRecord;
import com.example.password_manager.demo.password_manager.model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PasswordRecordRepository extends JpaRepository<PasswordRecord, UUID> {
    PasswordRecord findByServiceNameAndVault(String serviceName,Vault vault);



}
