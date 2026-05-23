package com.ocp.stage.suivistagiaires.config;

import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.repository.EncadrantRepository;
import com.ocp.stage.suivistagiaires.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EncadrantRepository encadrantRepository;

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Override
    public void run(String... args) throws Exception {
        if (encadrantRepository.count() == 0) {
            initializeEncadrants();
        }

        if (stagiaireRepository.count() == 0) {
            initializeStagiaires();
        }
    }

    private void initializeEncadrants() {
        System.out.println("=== Initialisation des encadrants de test ===");

        Encadrant enc1 = new Encadrant(
            "M. Ahmed Bennani", 
            "ahmed.bennani@ocp.ma", 
            "pass123", 
            "Genie Informatique", 
            "Direction IT"
        );
        enc1.setMaxStagiaires(5);

        Encadrant enc2 = new Encadrant(
            "Mme Fatima Zahra El Alami", 
            "fatima.elalami@ocp.ma", 
            "pass123", 
            "Genie Chimique", 
            "R&D Chimie"
        );
        enc2.setMaxStagiaires(3);

        Encadrant enc3 = new Encadrant(
            "Mohammed Tazi", 
            "mohammed.tazi@ocp.ma", 
            "pass123", 
            "Data Science", 
            "Analytics"
        );
        enc3.setMaxStagiaires(4);

        Encadrant enc4 = new Encadrant(
            "Amal Idrissi", 
            "amal.idrissi@ocp.ma", 
            "pass123", 
            "Gestion de Projet", 
            "PMO"
        );
        enc4.setMaxStagiaires(6);

        Encadrant enc5 = new Encadrant(
            "Youssef Berrada", 
            "youssef.berrada@ocp.ma", 
            "pass123", 
            "Cybersecurite", 
            "Securite IT"
        );
        enc5.setMaxStagiaires(2);

        encadrantRepository.save(enc1);
        encadrantRepository.save(enc2);
        encadrantRepository.save(enc3);
        encadrantRepository.save(enc4);
        encadrantRepository.save(enc5);

        System.out.println(">>> 5 encadrants crees avec succes !");
        System.out.println(">>> Emails: ahmed.bennani@ocp.ma, fatima.elalami@ocp.ma, ...");
        System.out.println(">>> Mot de passe pour tous: pass123");
    }

    private void initializeStagiaires() {
        System.out.println("=== Initialisation des stagiaires de test ===");

        Stagiaire stag1 = new Stagiaire();
        stag1.setNom("Alaoui");
        stag1.setPrenom("Mehdi");
        stag1.setEmail("mehdi.alaoui@ensa.ac.ma");
        stag1.setMotDePasse("test123");
        stag1.setTelephone("0612345678");
        stag1.setEcole("ENSA Casablanca");
        stag1.setSpecialite("Genie Informatique");
        stag1.setDateDebut(LocalDate.now().plusDays(7));
        stag1.setDateFin(LocalDate.now().plusMonths(2));
        stag1.setMotivation("Passionne par le developpement logiciel");
        stag1.setValide(false);

        Stagiaire stag2 = new Stagiaire();
        stag2.setNom("Sabri");
        stag2.setPrenom("Salma");
        stag2.setEmail("salma.sabri@emines.um6p.ma");
        stag2.setMotDePasse("test123");
        stag2.setTelephone("0623456789");
        stag2.setEcole("EMINES");
        stag2.setSpecialite("Data Science");
        stag2.setDateDebut(LocalDate.now().plusDays(14));
        stag2.setDateFin(LocalDate.now().plusMonths(3));
        stag2.setMotivation("Interessee par l analyse de donnees");
        stag2.setValide(false);

        Stagiaire stag3 = new Stagiaire();
        stag3.setNom("Bennani");
        stag3.setPrenom("Karim");
        stag3.setEmail("karim.bennani@ensam.ac.ma");
        stag3.setMotDePasse("test123");
        stag3.setTelephone("0634567890");
        stag3.setEcole("ENSAM Rabat");
        stag3.setSpecialite("Genie Industriel");
        stag3.setDateDebut(LocalDate.now());
        stag3.setDateFin(LocalDate.now().plusMonths(2));
        stag3.setMotivation("Ameliorer mes competences en gestion");
        stag3.setValide(true);

        stagiaireRepository.save(stag1);
        stagiaireRepository.save(stag2);
        stagiaireRepository.save(stag3);

        System.out.println(">>> 3 stagiaires crees avec succes !");
        System.out.println(">>> Stagiaire valide: karim.bennani@ensam.ac.ma");
        System.out.println(">>> Mot de passe: test123");
    }
}
