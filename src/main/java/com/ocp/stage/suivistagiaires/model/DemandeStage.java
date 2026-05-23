package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DemandeStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDemande;
    

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;   // EN_ATTENTE / VALIDEE / REFUSEE

    @OneToOne
    private Stagiaire stagiaire;
    
    @ManyToOne
    private Affectation affectation;

    
	public DemandeStage() {}
    // getters & setters …
    public Long getId() {
      	 return id ;
      }
       public void setId(Long id) {
      	 this.id=id ;
      }
    public    LocalDate getDateDemande() {
    	return dateDemande ;
    }
    public void setDateDemande(LocalDate dateDemande) {
    this.dateDemande=dateDemande ;
    }
    public Stagiaire getStagiaire() {
		return stagiaire;
	}
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	public StatutDemande getStatut() {
		return statut;
	}
	public void setStatut(StatutDemande statut) {
		this.statut = statut;
	}
	
}