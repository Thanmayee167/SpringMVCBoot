package com.example.springmvcboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvcboot.model.Alien;
import com.example.springmvcboot.repository.AlienRepository;

@RestController
@RequestMapping("/api/aliens")
public class AlienRestController {

    @Autowired
    private AlienRepository alienRepository;

    // Get all aliens
    @GetMapping(produces = {"application/xml"})
    public List<Alien> getAllAliens() {
        return alienRepository.findAll();
    }

    // Get alien by ID
    @GetMapping("/{id}")
    public ResponseEntity<Alien> getAlienById(@PathVariable("id") int id) {
        Optional<Alien> alien = alienRepository.findById(id);
        return alien.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Create new alien
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Alien createAlien(@RequestBody Alien alien) {
        return alienRepository.save(alien);
    }

    // Update alien
    @PutMapping("/{id}")
    public ResponseEntity<Alien> updateAlien(@PathVariable("id") int id, @RequestBody Alien alienDetails) {
        Optional<Alien> alien = alienRepository.findById(id);
        if (alien.isPresent()) {
            Alien updatedAlien = alien.get();
            updatedAlien.setAname(alienDetails.getAname());
            return ResponseEntity.ok(alienRepository.save(updatedAlien));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete alien
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlien(@PathVariable("id") int id) {
        Optional<Alien> alien = alienRepository.findById(id);
        if (alien.isPresent()) {
            alienRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Find by name
    @GetMapping("/search")
    public ResponseEntity<List<Alien>> findByName(@RequestParam String name) {
        List<Alien> aliens = alienRepository.findByAname(name);
        if (aliens != null && !aliens.isEmpty()) {
            return ResponseEntity.ok(aliens);
        }
        return ResponseEntity.notFound().build();
    }
} 