package com.example.demo.domain;

import com.example.demo.service.AccountCredentialsRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "account_credentials")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountCredentials{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;


    public AccountCredentials(AccountCredentialsRequest request) {
        this.email = request.getEmail();
        this.username = request.getUsername();
        this.password = request.getPassword();
    }
}