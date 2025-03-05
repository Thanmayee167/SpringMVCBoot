package com.example.springmvcboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springmvcboot.model.Alien;

@Repository
public interface AlienRepository extends JpaRepository<Alien, Integer> {
    
    /**
     * Find aliens by name, case insensitive.
     * 
     * @param aname the name to search for
     * @return List of matching aliens
     */
    @Query("SELECT a FROM Alien a WHERE LOWER(a.aname) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Alien> findByAname(@Param("name") String aname);

    /**
     * Find aliens by name and order by ID in descending order
     */
    List<Alien> findByAnameOrderByAidDesc(String aname);

    /**
     * Custom query example (same as findByAname but with explicit query)
     */
    @Query("from Alien where aname = :name")
    List<Alien> find(@Param("name") String aname);
}
