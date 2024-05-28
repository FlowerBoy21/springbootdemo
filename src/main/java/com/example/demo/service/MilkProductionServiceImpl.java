package com.example.demo.service;
import com.example.demo.domain.MilkProduction;
import com.example.demo.repo.MilkProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MilkProductionServiceImpl implements MilkProductionService {

    private final MilkProductionRepository milkProductionRepository;

    @Autowired
    public MilkProductionServiceImpl(MilkProductionRepository milkProductionRepository) {
        this.milkProductionRepository = milkProductionRepository;
    }

    @Override
    public List<MilkProduction> getAllMilkProductions() {
        return milkProductionRepository.findAll();
    }

    @Override
    public MilkProduction getMilkProductionById(int id) {
        return milkProductionRepository.findById(id).orElse(null);
    }

    @Override
    public MilkProduction createMilkProduction(MilkProduction milkProduction) {
        return milkProductionRepository.save(milkProduction);
    }

    @Override
    public MilkProduction updateMilkProduction(int id, MilkProduction updatedMilkProduction) {
        return milkProductionRepository.findById(id)
                .map(milkProduction -> {
                    milkProduction.setCow(updatedMilkProduction.getCow());
                    milkProduction.setDate(updatedMilkProduction.getDate());
                    milkProduction.setAmount(updatedMilkProduction.getAmount());
                    return milkProductionRepository.save(milkProduction);
                })
                .orElse(null);
    }

    @Override
    public void deleteMilkProduction(int id) {
        milkProductionRepository.deleteById(id);
    }
}
