package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.DemandeStage;
import com.ocp.stage.suivistagiaires.repository.DemandeStageRepository;

@Service
public class DemandeStageService {

    private final DemandeStageRepository demandeStageRepository;

    @Autowired
    public DemandeStageService(DemandeStageRepository demandeStageRepository) {
        this.demandeStageRepository = demandeStageRepository;
    }

    public DemandeStage save(DemandeStage demande) {
        return demandeStageRepository.save(demande);
    }

    public List<DemandeStage> findAll() {
        return demandeStageRepository.findAll();
    }

    public Optional<DemandeStage> findById(Long id) {
        return demandeStageRepository.findById(id);
    }

    public void deleteById(Long id) {
        demandeStageRepository.deleteById(id);
    }
}
