package com.ocp.stage.suivistagiaires.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {

    Optional<Stagiaire> findByEmail(String email);
    List<Stagiaire> findByEcole(String ecole);
    List<Stagiaire> findBySpecialite(String specialite);
    List<Stagiaire> findByProjets_Id(Long projetsId); // stagiaires d’un projet
    Stagiaire findByEmailAndMotDePasse(String email, String motDePasse);
    List<Stagiaire> findByValideFalse();

}
