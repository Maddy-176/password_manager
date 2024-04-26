package com.example.password_manager.demo.password_manager.controller;

import com.example.password_manager.demo.password_manager.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vault")
public class VaultController {

    @Autowired
    VaultService vaultService;
    @GetMapping("/")
    public void test(){
    }
}
