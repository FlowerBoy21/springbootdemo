package com.example.demo.service;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCredentialsRequestValidation {
    private String email;

    private String password;
}