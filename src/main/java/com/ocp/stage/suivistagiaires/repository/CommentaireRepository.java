package com.ocp.stage.suivistagiaires.repository;

import com.ocp.stage.suivistagiaires.model.Commentaire;
import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    
    // ✅ CORRECTION : Utiliser l'objet Stagiaire, pas l'ID
    List<Commentaire> findByStagiaire(Stagiaire stagiaire);
    
    // ✅ CORRECTION : Utiliser l'objet Encadrant, pas l'ID
    List<Commentaire> findByEncadrant(Encadrant encadrant);
}