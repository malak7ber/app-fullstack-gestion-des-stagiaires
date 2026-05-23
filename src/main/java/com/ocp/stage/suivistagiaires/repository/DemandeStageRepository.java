package com.ocp.stage.suivistagiaires.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.DemandeStage;

public interface DemandeStageRepository extends JpaRepository<DemandeStage, Long> {

    List<DemandeStage> findByStagiaire_Id(Long stagiaireId);
}
