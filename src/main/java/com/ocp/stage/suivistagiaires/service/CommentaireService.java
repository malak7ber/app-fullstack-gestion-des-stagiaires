package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.Commentaire;
import com.ocp.stage.suivistagiaires.repository.CommentaireRepository;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public Commentaire save(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    public Optional<Commentaire> findById(Long id) {
        return commentaireRepository.findById(id);
    }

    public void deleteById(Long id) {
        commentaireRepository.deleteById(id);
    }
}
