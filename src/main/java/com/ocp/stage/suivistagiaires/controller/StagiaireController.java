package com.ocp.stage.suivistagiaires.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.service.StagiaireService;


@RestController
@RequestMapping("/api/stagiaires")
public class StagiaireController {

    private final StagiaireService stagiaireService;

    @Autowired
    public StagiaireController(StagiaireService stagiaireService) {
        this.stagiaireService = stagiaireService;
    }

    @PostMapping
    public Stagiaire create(@RequestBody Stagiaire stagiaire) {
        return stagiaireService.save(stagiaire);
    }

    @GetMapping
    public List<Stagiaire> getAll() {
        return stagiaireService.findAll();
    }

    @GetMapping("/{id}")
    public Stagiaire findById(@PathVariable Long id) {
        return stagiaireService.findById(id).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stagiaireService.deleteById(id);
    }
}
