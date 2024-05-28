package com.example.demo.repo;

import com.example.demo.domain.AccountCredentials;
import com.example.demo.domain.Cow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCredentialsRepository extends JpaRepository<AccountCredentials, Integer> {

        AccountCredentials findByUsername(String username);
        AccountCredentials findByEmail(String email);
}
