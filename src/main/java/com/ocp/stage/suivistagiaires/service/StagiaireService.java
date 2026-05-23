package com.ocp.stage.suivistagiaires.service;

import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    public Stagiaire save(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    public List<Stagiaire> findAll() {
        return stagiaireRepository.findAll();
    }

    public Optional<Stagiaire> findById(Long id) {
        return stagiaireRepository.findById(id);
    }

    public void deleteById(Long id) {
        stagiaireRepository.deleteById(id);
    }

    public Optional<Stagiaire> findByEmail(String email) {
        return stagiaireRepository.findByEmail(email);
    }

    public Stagiaire findByEmailAndMotDePasse(String email, String motDePasse) {
        return stagiaireRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public void validerDemande(Long id) {
        Optional<Stagiaire> opt = stagiaireRepository.findById(id);
        if (opt.isPresent()) {
            Stagiaire s = opt.get();
            s.setValide(true);
            stagiaireRepository.save(s);
        }
    }

    public void refuserDemande(Long id) {
        stagiaireRepository.deleteById(id);
    }
}
