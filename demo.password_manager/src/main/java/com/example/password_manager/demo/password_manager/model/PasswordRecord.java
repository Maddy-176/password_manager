package com.example.password_manager.demo.password_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PasswordRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="vault_name",referencedColumnName = "name")
    private Vault vault;

    @Column(nullable = false)
    private String serviceName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;


}
