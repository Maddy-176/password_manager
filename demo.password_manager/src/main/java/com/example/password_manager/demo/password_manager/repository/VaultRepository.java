package com.example.password_manager.demo.password_manager.repository;

import com.example.password_manager.demo.password_manager.model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VaultRepository extends JpaRepository<Vault, UUID> {
    Vault getByName(String name);
}
