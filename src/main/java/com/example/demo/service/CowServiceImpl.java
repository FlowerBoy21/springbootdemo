package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Cow;
import com.example.demo.repo.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CowServiceImpl implements CowService {
    
    private final CowRepository cowRepository;

    @Autowired
    public CowServiceImpl(CowRepository cowRepository) {
        this.cowRepository = cowRepository;
    }

    @Override
    public List<Cow> getAllCows() {
        return cowRepository.findAll();
    }

    @Override
    public Cow getCowById(int id) {
        Optional<Cow> cowOptional = cowRepository.findById(id);
        return cowOptional.orElse(null);
    }
    

    @Override
    public Cow createCow(Cow cow) {
        // Validate cow object
        if (cow == null || cow.getName() == null || cow.getBreed() == null || cow.getaddress() == null) {
            throw new IllegalArgumentException("Invalid cow object. All fields (name, breed, address) are required.");
        }
        return cowRepository.save(cow);
    }

    @Override
    public Cow updateCow(int id, Cow updatedCow) {
        // Validate updatedCow object
        if (updatedCow == null || updatedCow.getName() == null || updatedCow.getBreed() == null || updatedCow.getaddress() == null) {
            throw new IllegalArgumentException("Invalid updated cow object. All fields (name, breed, address) are required.");
        }
        
        // Check if the cow with the given ID exists
        Optional<Cow> existingCowOptional = cowRepository.findById(id);
        if (existingCowOptional.isPresent()) {
            Cow existingCow = existingCowOptional.get();
            // Update existing cow with the fields from updatedCow
            existingCow.setName(updatedCow.getName());
            existingCow.setBreed(updatedCow.getBreed());
            existingCow.setAddress(updatedCow.getaddress());
            return cowRepository.save(existingCow);
        } else {
            throw new IllegalArgumentException("Cow with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteCow(int id) {
        // Check if the cow with the given ID exists
        if (cowRepository.existsById(id)) {
            cowRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cow with ID " + id + " not found.");
        }
    }
}
