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

import com.ocp.stage.suivistagiaires.model.DemandeStage;
import com.ocp.stage.suivistagiaires.service.DemandeStageService;

@RestController
@RequestMapping("/api/demandes")
public class DemandeStageController {

    private final DemandeStageService demandeStageService;

    @Autowired
    public DemandeStageController(DemandeStageService demandeStageService) {
        this.demandeStageService = demandeStageService;
    }

    @PostMapping
    public DemandeStage create(@RequestBody DemandeStage demande) {
        return demandeStageService.save(demande);
    }

    @GetMapping
    public List<DemandeStage> getAll() {
        return demandeStageService.findAll();
    }

    @GetMapping("/{id}")
    public DemandeStage getById(@PathVariable Long id) {
        return demandeStageService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        demandeStageService.deleteById(id);
    }
}
