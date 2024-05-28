
package com.example.demo.repo;

import com.example.demo.domain.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowRepository extends JpaRepository<Cow, Integer> {

    
}
