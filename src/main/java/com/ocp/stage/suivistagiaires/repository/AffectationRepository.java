package com.ocp.stage.suivistagiaires.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.Affectation;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    List<Affectation> findByEncadrant_Id(Long encadrantId);
    List<Affectation> findByStagiaire_Id(Long stagiaireId);
}
