package com.example.demo.domain;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

//create a class named Cow with id, name,breed,adress
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Cow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String breed;
    private String address;
    @OneToMany(mappedBy = "cow")
    @JsonManagedReference(value = "cowMilkProduction")
    private List<MilkProduction> milkProduction;

    public Cow() {
    }

    public Cow(int id, String name, String breed, String address, List<MilkProduction> milkProduction) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.address = address;
        this.milkProduction = milkProduction;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getaddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MilkProduction> getMilkProduction() {
        return milkProduction;
    }

    public void setMilkProduction(List<MilkProduction> milkProduction) {
        this.milkProduction = milkProduction;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", address='" + address + '\'' + ", milkProduction=" + milkProduction +
                '}';
    }
}