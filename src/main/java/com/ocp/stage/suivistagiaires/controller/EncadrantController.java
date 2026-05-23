package com.ocp.stage.suivistagiaires.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.service.EncadrantService;

@RestController
@RequestMapping("/api/encadrants")
public class EncadrantController {

    private final EncadrantService encadrantService;

    @Autowired
    public EncadrantController(EncadrantService encadrantService) {
        this.encadrantService = encadrantService;
    }

    @PostMapping
    public Encadrant create(@RequestBody Encadrant encadrant) {
        return encadrantService.save(encadrant);
    }

    @GetMapping
    public List<Encadrant> getAll() {
        return encadrantService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Encadrant> getById(@PathVariable Long id) {
        return encadrantService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        encadrantService.deleteById(id);
    }
}
