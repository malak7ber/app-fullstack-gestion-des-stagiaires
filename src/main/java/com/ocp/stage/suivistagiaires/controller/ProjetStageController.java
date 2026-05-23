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

import com.ocp.stage.suivistagiaires.model.ProjetStage;
import com.ocp.stage.suivistagiaires.service.ProjetStageService;

@RestController
@RequestMapping("/api/projets")
public class ProjetStageController {

    private final ProjetStageService projetStageService;

    @Autowired
    public ProjetStageController(ProjetStageService projetStageService) {
        this.projetStageService = projetStageService;
    }

    @PostMapping
    public ProjetStage create(@RequestBody ProjetStage projet) {
        return projetStageService.save(projet);
    }

    @GetMapping
    public List<ProjetStage> getAll() {
        return projetStageService.findAll();
    }

    @GetMapping("/{id}")
    public ProjetStage getById(@PathVariable Long id) {
        return projetStageService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        projetStageService.deleteById(id);
    }
}
