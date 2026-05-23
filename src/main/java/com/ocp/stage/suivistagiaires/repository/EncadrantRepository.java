package com.ocp.stage.suivistagiaires.repository;

import com.ocp.stage.suivistagiaires.model.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
    
    Encadrant findByEmailAndMotDePasse(String email, String motDePasse);
    
    Encadrant findByEmail(String email);
}