package com.example.demo.repo;

import com.example.demo.domain.MilkProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilkProductionRepository extends JpaRepository<MilkProduction, Integer> {

    
}
