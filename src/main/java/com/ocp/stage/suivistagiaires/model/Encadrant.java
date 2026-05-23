package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "encadrants")
public class Encadrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;
    private String specialite;
    private String service;
    private int maxStagiaires = 5;

    @OneToMany(mappedBy = "encadrant")
    private List<Commentaire> commentaires;

    @OneToMany(mappedBy = "encadrant", cascade = CascadeType.ALL)
    private List<CongéEncadrant> conges;

    @OneToMany(mappedBy = "encadrant")
    private List<ProjetStage> projets;
    
    @OneToMany(mappedBy = "encadrant")
    private List<Affectation> affectations;
    
    @OneToMany(mappedBy = "encadrant")
    private List<Stagiaire> stagiaires;

    public Encadrant() {}

    public Encadrant(String nom, String email, String motDePasse, String specialite, String service) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.specialite = specialite;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getMaxStagiaires() {
        return maxStagiaires;
    }

    public void setMaxStagiaires(int maxStagiaires) {
        this.maxStagiaires = maxStagiaires;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<CongéEncadrant> getConges() {
        return conges;
    }

    public void setConges(List<CongéEncadrant> conges) {
        this.conges = conges;
    }

    public List<ProjetStage> getProjets() {
        return projets;
    }

    public void setProjets(List<ProjetStage> projets) {
        this.projets = projets;
    }

    public List<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(List<Affectation> affectations) {
        this.affectations = affectations;
    }
    
    public boolean peutAccepterStagiaire() {
        return stagiaires == null || stagiaires.size() < maxStagiaires;
    }
    
    public int getNombreStagiaires() {
        return stagiaires == null ? 0 : stagiaires.size();
    }
}