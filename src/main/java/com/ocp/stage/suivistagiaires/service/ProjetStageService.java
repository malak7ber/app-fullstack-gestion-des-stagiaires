package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.ProjetStage;
import com.ocp.stage.suivistagiaires.repository.ProjetStageRepository;

@Service
public class ProjetStageService {

    private final ProjetStageRepository projetRepository;

    @Autowired
    public ProjetStageService(ProjetStageRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public ProjetStage save(ProjetStage projet) {
        return projetRepository.save(projet);
    }

    public List<ProjetStage> findAll() {
        return projetRepository.findAll();
    }

    public Optional<ProjetStage> findById(Long id) {
        return projetRepository.findById(id);
    }

    public void deleteById(Long id) {
        projetRepository.deleteById(id);
    }

    public List<ProjetStage> findByEncadrantId(Long id) {
        return projetRepository.findByEncadrant_Id(id);
    }
}
