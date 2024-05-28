package com.example.demo.component;


import com.example.demo.domain.AccountCredentials;
import com.example.demo.domain.Priority;
import com.example.demo.repo.PriorityRepository;
import com.example.demo.service.AccountCredentialsService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriorityInitialization {
    private final PriorityRepository priorityRepository;
    private final AccountCredentialsService accountCredentialsService;

    public PriorityInitialization(PriorityRepository priorityRepository, AccountCredentialsService accountCredentialsService) {
        this.priorityRepository = priorityRepository;
        this.accountCredentialsService = accountCredentialsService;
    }

    @PostConstruct
    public void init() {
        var allPriorities = priorityRepository.findAll();
        var users = allPriorities.stream().filter(priority -> priority.getName().equals("Users")).toList().isEmpty();
        var admins = allPriorities.stream().filter(priority -> priority.getName().equals("Admins")).toList().isEmpty();
        if(users){
            var priority = new Priority();
            priority.setName("Users");
            List<AccountCredentials> accountCredentials = accountCredentialsService.findAll().stream().filter(n->n.getId() != 0).toList();
            priority.setUsers(accountCredentials);
            priorityRepository.save(priority);
        }

        if(admins){
            var priority = new Priority();
            priority.setName("Admins");
            var accountCredentialsExistent = accountCredentialsService.findAll().stream().filter(n->n.getEmail().equals("admin.is@here.com")).toList();
            if(accountCredentialsExistent.isEmpty()) {
                var accountCredential = new AccountCredentials();
                accountCredential.setEmail("admin.is@here.com");
                accountCredential.setId(0);
                accountCredential.setPassword("admin");
                accountCredential.setUsername("It's Admin");
                accountCredentialsService.createAdmin(accountCredential);
                List<AccountCredentials> accountCredentials = List.of(accountCredential);
                priority.setUsers(accountCredentials);
            }else {
                var accountCredentialExist = accountCredentialsService.findAll().stream().filter(n->n.getEmail().equals("admin.is@here.com")).toList().get(0);
                List<AccountCredentials> accountCredentials = List.of(accountCredentialExist);
                priority.setUsers(accountCredentials);
            }


            priorityRepository.save(priority);
        }
    }
}
