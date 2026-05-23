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

import com.ocp.stage.suivistagiaires.model.Affectation;
import com.ocp.stage.suivistagiaires.service.AffectationService;

@RestController
@RequestMapping("/api/affectations")
public class AffectationController {

    private final AffectationService affectationService;

    @Autowired
    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @PostMapping("/assigner")
    public Affectation assignerStagiaire(@RequestBody Affectation affectation) {
        return affectationService.save(affectation);
    }


    @GetMapping
    public List<Affectation> getAll() {
        return affectationService.findAll();
    }

    @GetMapping("/{id}")
    public Affectation getById(@PathVariable Long id) {
        return affectationService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        affectationService.deleteById(id);
    }
}
