package com.example.demo.service;


import com.example.demo.domain.AccountCredentials;
import com.example.demo.repo.AccountCredentialsRepository;
import com.example.demo.repo.PriorityRepository;
import com.example.demo.security.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCredentialsService {
    private final AccountCredentialsRepository accountCredentialsRepository;
    private final TokenService tokenService;

    private final PriorityRepository priorityRepository;

    public AccountCredentialsService(AccountCredentialsRepository accountCredentialsRepository, TokenService tokenService, PriorityRepository priorityRepository) {
        this.accountCredentialsRepository = accountCredentialsRepository;
        this.tokenService = tokenService;
        this.priorityRepository = priorityRepository;
    }

    public void create(AccountCredentialsRequest request) throws Exception {
        AccountCredentials value = new AccountCredentials(request);
        if(accountCredentialsRepository.findByEmail(request.getEmail()) != null){
            throw new Exception("Already Existent Email!");
        }
        accountCredentialsRepository.save(value);
    }

    public TokenDTO logIn(AccountCredentialsRequestValidation requestValidation) {
        var account = accountCredentialsRepository.findByEmail(requestValidation.getEmail());
        return account.getPassword().equals(requestValidation.getPassword()) ? getToken(account) : null;
    }


    private TokenDTO getToken(AccountCredentials accountCredentials) {
        var token = tokenService.getTokenByUserId(accountCredentials.getId());
        if (token != null) {
            return new TokenDTO(token.getToken());
        }
        return new TokenDTO(this.tokenService.generateToken(accountCredentials));
    }

    public AccountCredentials returnByEmail(String email) {
        return accountCredentialsRepository.findByEmail(email);
    }

    public AccountCredentials returnById(int id) {
        return accountCredentialsRepository.findById(id).orElse(null);
    }

    private void addUserToAdmins(AccountCredentials accountCredentials) {
        var priorities = priorityRepository.findAll();
        for (var priority : priorities) {
            if(priority.getName().equals("Admins")){
                priority.getUsers().add(accountCredentials);
            }
            priorityRepository.save(priority);
        }
    }

    public List<AccountCredentials> findAll() {
        return accountCredentialsRepository.findAll();
    }

    public void createAdmin(AccountCredentials accountCredential) {
        accountCredentialsRepository.save(accountCredential);
    }
}
