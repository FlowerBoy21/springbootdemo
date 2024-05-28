package com.example.demo.service;

import jakarta.persistence.Column;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountCredentialsRequest {

    private String email;

    private String username;

    private String password;
}
