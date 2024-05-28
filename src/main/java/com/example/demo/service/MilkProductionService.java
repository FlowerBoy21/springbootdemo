package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MilkProduction;
import org.springframework.stereotype.Component;

@Component
public interface MilkProductionService {
    List<MilkProduction> getAllMilkProductions();
    MilkProduction getMilkProductionById(int id);
    MilkProduction createMilkProduction(MilkProduction milkProduction);
    MilkProduction updateMilkProduction(int id, MilkProduction milkProduction);
    void deleteMilkProduction(int id);
}
