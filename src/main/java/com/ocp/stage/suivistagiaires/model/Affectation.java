package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Affectation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAffectation;

    @ManyToOne
    private Stagiaire stagiaire;

    @ManyToOne
    private Encadrant encadrant;
    
    @OneToMany(mappedBy = "affectation")
    private List<DemandeStage> demandes;

    public Affectation() {}
    // getters & setters …
    public Long getId() {
      	 return id;
       }
       public void setId(Long id) {
      	 this.id=id;
       }
       public LocalDate getDateAffectation() {
       	return dateAffectation;
       }
       public void setDateAffectation(LocalDate dateAffectation) {
       	this.dateAffectation=dateAffectation;
       }
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	public Encadrant getEncadrant() {
		return encadrant;
	}
	public void setEncadrant(Encadrant encadrant) {
		this.encadrant = encadrant;
	}
	public List<DemandeStage> getDemandes() {
		return demandes;
	}
	public void setDemandes(List<DemandeStage> demandes) {
		this.demandes = demandes;
	}
       
}

