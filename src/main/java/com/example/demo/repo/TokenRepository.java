package com.example.demo.repo;

import com.example.demo.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    Token findByToken(String token);

    Token findByUserID(int id);

    void deleteByToken(String token);
}
