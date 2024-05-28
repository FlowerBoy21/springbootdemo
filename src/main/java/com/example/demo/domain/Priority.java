package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "priority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<AccountCredentials> users;
}
