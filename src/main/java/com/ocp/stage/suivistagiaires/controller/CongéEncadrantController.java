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

import com.ocp.stage.suivistagiaires.model.CongéEncadrant;
import com.ocp.stage.suivistagiaires.service.CongéEncadrantService;

@RestController
@RequestMapping("/api/conges")
public class CongéEncadrantController {

    private final CongéEncadrantService congéEncadrantService;

    @Autowired
    public CongéEncadrantController(CongéEncadrantService congéEncadrantService) {
        this.congéEncadrantService = congéEncadrantService;
    }

    @PostMapping
    public CongéEncadrant create(@RequestBody CongéEncadrant conge) {
        return congéEncadrantService.save(conge);
    }

    @GetMapping
    public List<CongéEncadrant> getAll() {
        return congéEncadrantService.findAll();
    }

    @GetMapping("/{id}")
    public CongéEncadrant getById(@PathVariable Long id) {
        return congéEncadrantService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        congéEncadrantService.deleteById(id);
    }
}
