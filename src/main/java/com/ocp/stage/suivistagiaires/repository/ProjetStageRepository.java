package com.ocp.stage.suivistagiaires.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.ProjetStage;

public interface ProjetStageRepository extends JpaRepository<ProjetStage, Long> {

    List<ProjetStage> findByEncadrant_Id(Long encadrantId);
    
}
