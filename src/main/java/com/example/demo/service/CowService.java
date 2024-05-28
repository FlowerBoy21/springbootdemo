
package com.example.demo.service;
import java.util.List;

import com.example.demo.domain.Cow;
import org.springframework.stereotype.Component;

@Component
public interface CowService {
    List<Cow> getAllCows();
    Cow getCowById(int id);
    Cow createCow(Cow cow);
    Cow updateCow(int id, Cow cow);
    void deleteCow(int id);
}