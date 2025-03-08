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

  @Autowired private AlienRepository alienRepository;

  // Get all aliens - demonstrates normal execution
  @GetMapping(produces = {"application/xml"})
  public List<Alien> getAllAliens() {
    return alienRepository.findAll();
  }

  // Get alien by ID - demonstrates exception throwing
  @GetMapping("/{id}")
  public ResponseEntity<Alien> getAlienById(@PathVariable("id") int id) {
    Optional<Alien> alien = alienRepository.findById(id);
    return alien
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RuntimeException("Alien not found with id: " + id));
  }

  // Create new alien - demonstrates successful creation
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public Alien createAlien(@RequestBody Alien alien) {
    if (alien.getAname() == null || alien.getAname().trim().isEmpty()) {
      throw new IllegalArgumentException("Alien name cannot be empty");
    }
    return alienRepository.save(alien);
  }

  // Update alien - demonstrates conditional execution
  @PutMapping("/{id}")
  public ResponseEntity<Alien> updateAlien(
      @PathVariable("id") int id, @RequestBody Alien alienDetails) {
    return alienRepository
        .findById(id)
        .map(
            existingAlien -> {
              existingAlien.setAname(alienDetails.getAname());
              return ResponseEntity.ok(alienRepository.save(existingAlien));
            })
        .orElseThrow(() -> new RuntimeException("Cannot update. Alien not found with id: " + id));
  }

  // Delete alien - demonstrates void return type
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAlien(@PathVariable("id") int id) {
    if (!alienRepository.existsById(id)) {
      throw new RuntimeException("Cannot delete. Alien not found with id: " + id);
    }
    alienRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  // Find by name - demonstrates list return with potential empty result
  @GetMapping("/search")
  public ResponseEntity<List<Alien>> findByName(@RequestParam String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Search name cannot be empty");
    }
    List<Alien> aliens = alienRepository.findByAname(name);
    if (aliens.isEmpty()) {
      throw new RuntimeException("No aliens found with name: " + name);
    }
    return ResponseEntity.ok(aliens);
  }
}
