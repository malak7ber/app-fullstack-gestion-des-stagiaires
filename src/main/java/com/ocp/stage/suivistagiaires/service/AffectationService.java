package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.Affectation;
import com.ocp.stage.suivistagiaires.repository.AffectationRepository;

@Service
public class AffectationService {

    private final AffectationRepository affectationRepository;

    @Autowired
    public AffectationService(AffectationRepository affectationRepository) {
        this.affectationRepository = affectationRepository;
    }

    public Affectation save(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    public List<Affectation> findAll() {
        return affectationRepository.findAll();
    }

    public Optional<Affectation> findById(Long id) {
        return affectationRepository.findById(id);
    }

    public void deleteById(Long id) {
        affectationRepository.deleteById(id);
    }
}
