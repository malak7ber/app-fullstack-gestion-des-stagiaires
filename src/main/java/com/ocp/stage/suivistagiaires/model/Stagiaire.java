package com.ocp.stage.suivistagiaires.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity 

public class Stagiaire {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;
	    private String prenom;
	    private String email;
	    private String telephone;
	    private String ecole;
	    private String specialite;
	    private LocalDate dateDebut;
	    private LocalDate dateFin;
        private String motDePasse;
        private String motivation;
        private boolean valide = false;

        


        @ManyToMany
        private List<ProjetStage> projets;
        
        @ManyToOne
        @JoinColumn(name = "encadrant_id")
        private Encadrant encadrant;

       
        public Stagiaire() {} // constructeur vide obligatoire

        
 public Long getId() {
	 return id ;
}
 public void setId(Long id) {
	 this.id=id ;
}
 
 public Encadrant getEncadrant() {
	return encadrant;
}


public void setEncadrant(Encadrant encadrant) {
	this.encadrant = encadrant;
}


public String getNom() {
	 return nom ;
}
 public void setNom(String nom) {
	 this.nom=nom ;
}
 public String getPrenom() {
	 return prenom ;
}
 public void setPrenom(String prenom) {
	 this.prenom=prenom ;
}
 public String getEmail() {
	 return email ;
}
 public void setEmail(String email) {
	 this.email=email ;
}
 public String getEcole() {
	 return ecole ;
}
 public void setEtablissement(String ecole) {
	 this.ecole=ecole ;
}
 public String getSpecialite() {
	 return specialite ;
}
 public void setSpecialite(String specialite) {
	 this.specialite= specialite ;
}
 
 public LocalDate  getDateDebut() {
	 return dateDebut ;
}
 public void setDateDebut(LocalDate dateDebut) {
	 this.dateDebut= dateDebut ;
}
 public LocalDate  getDateFin() {
	 return dateFin ;
}
 public void setDateFin(LocalDate dateFin) {
	 this.dateFin= dateFin ;
}
 public String getMotDePasse() {
	 return motDePasse ;
 }
 public void setMotDePasse(String motDePasse) {
	 this.motDePasse=motDePasse ;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getMotivation() {
	return motivation;
}
public void setMotivation(String motivation) {
	this.motivation = motivation;
}


public List<ProjetStage> getProjets() {
	return projets;
}


public void setProjets(List<ProjetStage> projets) {
	this.projets = projets;
}


public void setEcole(String ecole) {
	this.ecole = ecole;
}
public boolean isValide() {
    return valide;
}

public void setValide(boolean valide) {
    this.valide = valide;
}
private boolean attestationDisponible = false;

public boolean isAttestationDisponible() {
    return attestationDisponible;
}

public void setAttestationDisponible(boolean attestationDisponible) {
    this.attestationDisponible = attestationDisponible;
}
private boolean rapportValide = false;

public boolean isRapportValide() {
    return rapportValide;
}

public void setRapportValide(boolean rapportValide) {
    this.rapportValide = rapportValide;
}
}
