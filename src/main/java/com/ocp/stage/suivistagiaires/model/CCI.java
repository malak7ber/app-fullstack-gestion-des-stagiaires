package com.ocp.stage.suivistagiaires.model;



import jakarta.persistence.*;

@Entity
public class CCI {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    
   
    public CCI() {}
    // getters & setters …
    
    public long getId() {
      	 return id ;
      }
       public void setId(Long id) {
      	 this.id=id ;
      }
       public String getNom() {
      	 return nom ;
      }
       public void setNom(String nom) {
      	 this.nom=nom ;
      }
       public String getEmail() {
      	 return email ;
      }
       public void setEmail(String email) {
      	 this.email=email ;
      }
       private String motDePasse;

       public String getMotDePasse() {
           return motDePasse;
       }

       public void setMotDePasse(String motDePasse) {
           this.motDePasse = motDePasse;
       }
}