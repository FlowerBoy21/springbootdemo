package com.example.demo.repo;

import com.example.demo.domain.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
