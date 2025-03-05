package com.example.springmvcboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springmvcboot.model.Alien;

@Repository
public interface AlienRepository extends JpaRepository<Alien, Integer> {
}
