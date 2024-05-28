package com.example.demo.controller;

import com.example.demo.domain.Cow;
import com.example.demo.domain.MilkProduction;
import com.example.demo.service.CowService;
import com.example.demo.service.MilkProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milk-products")
public class MilkProductionController {

    private final MilkProductionService milkProductionService;
    private final CowService cowService; // Add this line

    @Autowired
    public MilkProductionController(MilkProductionService milkProductionService, CowService cowService) {
        this.milkProductionService = milkProductionService;
        this.cowService = cowService; // Initialize cowService
    }

    @GetMapping
    public ResponseEntity<List<MilkProduction>> getAllMilkProductions() {
        List<MilkProduction> milkProductions = milkProductionService.getAllMilkProductions();
        return ResponseEntity.ok(milkProductions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilkProduction> getMilkProductionById(@PathVariable int id) {
        MilkProduction milkProduction = milkProductionService.getMilkProductionById(id);
        if (milkProduction != null) {
            return ResponseEntity.ok(milkProduction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MilkProduction> createMilkProduction(@RequestBody MilkProduction milkProduction) {
        try {
            // Fetch the cow object from the provided cowId
            Cow cow = cowService.getCowById(milkProduction.getCow().getId());
            if (cow != null) {
                // Set the cow object to the milk production
                milkProduction.setCow(cow);
                System.out.println("Received Milk Production: " + milkProduction);
                MilkProduction createdMilkProduction = milkProductionService.createMilkProduction(milkProduction);
                System.out.println("Created Milk Production: " + createdMilkProduction);
                return new ResponseEntity<>(createdMilkProduction, HttpStatus.CREATED);
            } else {
                // Return an error response if the cow with the provided ID is not found
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            System.err.println("Error creating milk production: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilkProduction> updateMilkProduction(@PathVariable int id, @RequestBody MilkProduction milkProduction) {
        MilkProduction updatedMilkProduction = milkProductionService.updateMilkProduction(id, milkProduction);
        if (updatedMilkProduction != null) {
            return ResponseEntity.ok(updatedMilkProduction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilkProduction(@PathVariable int id) {
        milkProductionService.deleteMilkProduction(id);
        return ResponseEntity.noContent().build();
    }
}
