package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    private LocalDate date;

    // ✅ CORRECTION : Relations avec les entités, pas avec Long
    @ManyToOne
    @JoinColumn(name = "encadrant_id")
    private Encadrant encadrant;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id")
    private Stagiaire stagiaire;

    // Constructeurs
    public Commentaire() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    // Méthodes utilitaires pour compatibility avec l'ancien code
    public void setEncadrantId(Long encadrantId) {
        // Cette method est gardée pour compatibilité mais n'est plus utilisée
        // L'encadrant doit être défini via setEncadrant()
    }

    public void setStagiaireId(Long stagiaireId) {
        // Cette méthode est gardée pour compatibilité mais n'est plus utilisée
        // Le stagiaire doit être défini via setStagiaire()
    }
    private String reponse;
    private LocalDate dateReponse;

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public LocalDate getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
    }
}