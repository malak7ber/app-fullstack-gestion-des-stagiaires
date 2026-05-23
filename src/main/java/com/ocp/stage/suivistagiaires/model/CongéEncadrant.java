package com.ocp.stage.suivistagiaires.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CongéEncadrant {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    private Encadrant encadrant;

    public CongéEncadrant() {}
    // getters & setters …
    
    
    public Long getId() {
   	 return id ;
   }
    public void setId(Long id) {
   	 this.id=id ;
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


	public Encadrant getEncadrant() {
		return encadrant;
	}


	public void setEncadrant(Encadrant encadrant) {
		this.encadrant = encadrant;
	}
    
}
