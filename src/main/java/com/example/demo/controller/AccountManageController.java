package com.example.demo.controller;


import com.example.demo.service.AccountCredentialsRequest;
import com.example.demo.service.AccountCredentialsRequestValidation;
import com.example.demo.service.AccountCredentialsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.TokenDTO;

@RestController
@RequestMapping(value = "/app/v1/accounts")
public class AccountManageController {
    private final AccountCredentialsService accountCredentialsService;


    public AccountManageController(AccountCredentialsService accountCredentialsService) {
        this.accountCredentialsService = accountCredentialsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody AccountCredentialsRequest request) {
        try {
            accountCredentialsService.create(request);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> logIn(@RequestBody AccountCredentialsRequestValidation requestValidation) {
        try {
            var value = accountCredentialsService.logIn(requestValidation);

            if (value==null) {
                throw new Exception("Invalid!");
            }

            return ResponseEntity.ok().body(value);
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/valid-token")
    public ResponseEntity validToken() {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}