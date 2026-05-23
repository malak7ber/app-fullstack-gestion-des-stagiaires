package com.ocp.stage.suivistagiaires.model;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class Document {
 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Enumerated(EnumType.STRING)
 private TypeDoc type;
 
 private String fichier;
 private LocalDate dateDepot;
 
 @ManyToOne
 private Stagiaire stagiaire ;
 
 public Document() {}
 
 //getterssetters
 
 public Long getId() {
	 return id;
 }
 public void setId(Long id) {
	 this.id=id;
 }
 public String getFichier() {
	 return fichier;
 }
 public void setFichier(String fichier) {
	 this.fichier=fichier;
 }
public LocalDate getDateDepot() {
	return dateDepot;
}
public void setDateDepot(LocalDate dateDepot) {
	this.dateDepot=dateDepot;
}
public TypeDoc getType() {
    return type;
}
public void setType(TypeDoc type) {
    this.type = type;
}

public Stagiaire getStagiaire() {
    return stagiaire;
}
public void setStagiaire(Stagiaire stagiaire) {
    this.stagiaire = stagiaire;
}

}
