package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // VALIDATION_RAPPORT, NOUVEAU_STAGIAIRE, etc.
    private String message;
    private LocalDate date;
    private boolean lue = false;

    @ManyToOne
    private Stagiaire stagiaire;

    // Constructeurs
    public Notification() {}

    public Notification(String type, String message, Stagiaire stagiaire) {
        this.type = type;
        this.message = message;
        this.stagiaire = stagiaire;
        this.date = LocalDate.now();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isLue() {
        return lue;
    }

    public void setLue(boolean lue) {
        this.lue = lue;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }
}