package com.ocp.stage.suivistagiaires.controller;

import com.ocp.stage.suivistagiaires.model.Affectation;
import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.service.AffectationService;
import com.ocp.stage.suivistagiaires.service.EncadrantService;
import com.ocp.stage.suivistagiaires.service.StagiaireService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cci")
public class CCIController {

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private EncadrantService encadrantService;

    @Autowired
    private AffectationService affectationService;

    // ✅ NOUVEAU : Page de connexion CCI
    @GetMapping("/connexion")
    public String showLoginForm() {
        return "login-cci";
    }

    // ✅ NOUVEAU : Traiter le login CCI
    @PostMapping("/login")
    public String loginCCI(@RequestParam String email,
                           @RequestParam String motDePasse,
                           Model model,
                           HttpSession session) {
        
        if ("admin@ocp.ma".equals(email) && "admin123".equals(motDePasse)) {
            session.setAttribute("cciConnecte", true);
            return "redirect:/cci";
        }
        
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "login-cci";
    }

    // ✅ MODIFIÉ : Dashboard avec vérification
    @GetMapping
    public String cciDashboard(HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }
        return "CCI-dashboard";
    }

    @GetMapping("/demandes")
    public String voirDemandes(Model model, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        List<Stagiaire> demandes = stagiaireService.findAll()
                .stream()
                .filter(s -> !s.isValide())
                .collect(Collectors.toList());
        model.addAttribute("demandes", demandes);
        return "cci-demandes";
    }

    @PostMapping("/valider/{id}")
    public String validerDemande(@PathVariable Long id, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        Stagiaire stagiaire = stagiaireService.findById(id).orElse(null);
        if (stagiaire != null) {
            stagiaire.setValide(true);
            stagiaireService.save(stagiaire);
        }
        return "redirect:/cci/demandes";
    }

    @PostMapping("/rejeter/{id}")
    public String rejeterDemande(@PathVariable Long id, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        stagiaireService.deleteById(id);
        return "redirect:/cci/demandes";
    }

    @GetMapping("/affecter")
    public String affecterPage(Model model, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        List<Stagiaire> stagiaires = stagiaireService.findAll()
                .stream()
                .filter(s -> s.isValide() && s.getEncadrant() == null)
                .collect(Collectors.toList());

        List<Encadrant> encadrants = encadrantService.findAll()
                .stream()
                .filter(e -> e.peutAccepterStagiaire())
                .collect(Collectors.toList());

        model.addAttribute("stagiaires", stagiaires);
        model.addAttribute("encadrants", encadrants);
        return "cci-affecter";
    }

    @PostMapping("/affecter")
    public String traiterAffectation(@RequestParam Long stagiaireId, 
                                     @RequestParam Long encadrantId,
                                     Model model,
                                     HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        Stagiaire stagiaire = stagiaireService.findById(stagiaireId).orElse(null);
        Encadrant encadrant = encadrantService.findById(encadrantId).orElse(null);

        if (stagiaire != null && encadrant != null) {
            if (!encadrant.peutAccepterStagiaire()) {
                model.addAttribute("error", "Cet encadrant a atteint son nombre maximum");
                return affecterPage(model, session);
            }

            Affectation affectation = new Affectation();
            affectation.setStagiaire(stagiaire);
            affectation.setEncadrant(encadrant);
            affectation.setDateAffectation(LocalDate.now());
            affectationService.save(affectation);

            stagiaire.setEncadrant(encadrant);
            stagiaireService.save(stagiaire);
        }

        return "redirect:/cci/affecter";
    }

    @GetMapping("/stagiaires")
    public String voirStagiaires(Model model, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        List<Stagiaire> stagiaires = stagiaireService.findAll();
        model.addAttribute("stagiaires", stagiaires);
        return "cci-stagiaire";
    }

    @GetMapping("/encadrants")
    public String voirEncadrants(Model model, HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        List<Encadrant> encadrants = encadrantService.findAll();
        model.addAttribute("encadrants", encadrants);
        return "cci-encadrants";
    }

    @GetMapping("/attestation")
    public String attestationPage(HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        return "cci-attestation";
    }

    // ✅ NOUVEAU : Logout CCI
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("cciConnecte");
        return "redirect:/";
    }
    @GetMapping("/notifications")
    public String notifications(HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }
        return "cci-notifications";
    }

    @PostMapping("/generer-attestation/{id}")
    public String genererAttestation(@PathVariable Long id, 
                                     HttpSession session,
                                     Model model) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }

        Stagiaire stagiaire = stagiaireService.findById(id).orElse(null);
        if (stagiaire != null && stagiaire.isRapportValide()) {
            stagiaire.setAttestationDisponible(true);
            stagiaireService.save(stagiaire);
            model.addAttribute("success", "Attestation rendue disponible pour " + 
                              stagiaire.getPrenom() + " " + stagiaire.getNom());
        }

        return "redirect:/cci/notifications";
    }
    @GetMapping("/suivi-complet")
    public String suiviComplet(HttpSession session) {
        Boolean cciConnecte = (Boolean) session.getAttribute("cciConnecte");
        if (cciConnecte == null || !cciConnecte) {
            return "redirect:/cci/connexion";
        }
        return "cci-suivi-complet";
    }
}