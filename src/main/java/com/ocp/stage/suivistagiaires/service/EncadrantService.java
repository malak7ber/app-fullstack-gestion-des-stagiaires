package com.ocp.stage.suivistagiaires.service;

import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.repository.EncadrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncadrantService {

    @Autowired
    private EncadrantRepository encadrantRepository;

    public Encadrant save(Encadrant encadrant) {
        return encadrantRepository.save(encadrant);
    }

    public List<Encadrant> findAll() {
        return encadrantRepository.findAll();
    }

    public Optional<Encadrant> findById(Long id) {
        return encadrantRepository.findById(id);
    }

    public void deleteById(Long id) {
        encadrantRepository.deleteById(id);
    }

    public Encadrant findByEmailAndMotDePasse(String email, String motDePasse) {
        return encadrantRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public Encadrant findByEmail(String email) {
        return encadrantRepository.findByEmail(email);
    }
}