package com.example.demo.controller;

import com.example.demo.domain.Cow;
import com.example.demo.service.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cows")
public class CowController {

    private final CowService cowService;

    @Autowired
    public CowController(CowService cowService) {
        this.cowService = cowService;
    }

    @GetMapping
    public ResponseEntity<List<Cow>> getAllCows() {
        List<Cow> cows = cowService.getAllCows();
        return ResponseEntity.ok(cows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cow> getCowById(@PathVariable int id) {
        Cow cow = cowService.getCowById(id);
        if (cow != null) {
            return ResponseEntity.ok(cow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/aa")
    public void getTest() {
        System.out.println(1);
//        Cow cow = cowService.getCowById(id);
//        if (cow != null) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping
    public ResponseEntity<Cow> createCow(@RequestBody Cow cow) {
        Cow createdCow = cowService.createCow(cow);
        return new ResponseEntity<>(createdCow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cow> updateCow(@PathVariable int id, @RequestBody Cow cow) {
        Cow updatedCow = cowService.updateCow(id, cow);
        if (updatedCow != null) {
            return ResponseEntity.ok(updatedCow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCow(@PathVariable int id) {
        cowService.deleteCow(id);
        return ResponseEntity.noContent().build();
    }
}
