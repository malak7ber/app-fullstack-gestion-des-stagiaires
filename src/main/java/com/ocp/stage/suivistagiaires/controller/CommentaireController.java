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

import com.ocp.stage.suivistagiaires.model.Commentaire;
import com.ocp.stage.suivistagiaires.service.CommentaireService;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping
    public Commentaire create(@RequestBody Commentaire commentaire) {
        return commentaireService.save(commentaire);
    }

    @GetMapping
    public List<Commentaire> getAll() {
        return commentaireService.findAll();
    }

    @GetMapping("/{id}")
    public Commentaire getById(@PathVariable Long id) {
        return commentaireService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        commentaireService.deleteById(id);
    }
}

