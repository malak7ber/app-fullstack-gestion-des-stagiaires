package com.ocp.stage.suivistagiaires.controller;

import com.ocp.stage.suivistagiaires.model.Commentaire;
import com.ocp.stage.suivistagiaires.model.CongéEncadrant;
import com.ocp.stage.suivistagiaires.model.Encadrant;
import com.ocp.stage.suivistagiaires.model.Notification;
import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.repository.CommentaireRepository;
import com.ocp.stage.suivistagiaires.repository.NotificationRepository;
import com.ocp.stage.suivistagiaires.service.CongéEncadrantService;
import com.ocp.stage.suivistagiaires.service.EncadrantService;
import com.ocp.stage.suivistagiaires.service.StagiaireService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/encadrant")
public class EncadrantPageController {

    @Autowired
    private EncadrantService encadrantService;

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private CongéEncadrantService congéService;

    @GetMapping
    public String encadrantHome() {
        return "encadrant-home";
    }

    @GetMapping("/connexion")
    public String showLoginForm() {
        return "login-encadrant";
    }

    @PostMapping("/login")
    public String loginEncadrant(@RequestParam String email,
                                 @RequestParam String motDePasse,
                                 Model model,
                                 HttpSession session) {
        
        Encadrant encadrant = encadrantService.findByEmailAndMotDePasse(email, motDePasse);
        
        if (encadrant == null) {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login-encadrant";
        }

        session.setAttribute("encadrantConnecte", encadrant);
        return "redirect:/encadrant/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        
        model.addAttribute("encadrant", encadrant);
        model.addAttribute("nombreStagiaires", encadrant.getNombreStagiaires());
        model.addAttribute("maxStagiaires", encadrant.getMaxStagiaires());
        
        return "encadrant-dashboard";
    }

    @GetMapping("/stagiaires")
    public String voirStagiaires(Model model, HttpSession session) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        List<Stagiaire> stagiaires = encadrant.getStagiaires();

        model.addAttribute("stagiaires", stagiaires);
        model.addAttribute("encadrant", encadrant);
        
        return "encadrant-stagiaires";
    }

    @GetMapping("/parametres")
    public String parametres(Model model, HttpSession session) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        model.addAttribute("encadrant", encadrant);
        
        return "encadrant-parametres";
    }

    @PostMapping("/parametres/max-stagiaires")
    public String updateMaxStagiaires(@RequestParam int maxStagiaires,
                                      HttpSession session,
                                      Model model) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        encadrant.setMaxStagiaires(maxStagiaires);
        encadrantService.save(encadrant);

        session.setAttribute("encadrantConnecte", encadrant);
        model.addAttribute("success", "Nombre maximum mis a jour");
        
        return "redirect:/encadrant/parametres";
    }

    @PostMapping("/parametres/conge")
    public String declarerConge(@RequestParam LocalDate dateDebut,
                               @RequestParam LocalDate dateFin,
                               HttpSession session,
                               Model model) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        
        CongéEncadrant conge = new CongéEncadrant();
        conge.setEncadrant(encadrant);
        conge.setDateDebut(dateDebut);
        conge.setDateFin(dateFin);
        
        congéService.save(conge);

        model.addAttribute("success", "Periode de conge enregistree");
        return "redirect:/encadrant/parametres";
    }

    @GetMapping("/commenter")
    public String commenterPage(Model model, HttpSession session) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        List<Stagiaire> stagiaires = encadrant.getStagiaires();

        model.addAttribute("stagiaires", stagiaires);
        return "encadrant-commenter";
    }

    @PostMapping("/commenter")
    public String ajouterCommentaire(@RequestParam Long stagiaireId,
                                     @RequestParam String contenu,
                                     HttpSession session,
                                     Model model) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        Stagiaire stagiaire = stagiaireService.findById(stagiaireId).orElse(null);
        
        if (stagiaire != null && encadrant != null) {
            Commentaire commentaire = new Commentaire();
            commentaire.setEncadrant(encadrant);
            commentaire.setStagiaire(stagiaire);
            commentaire.setContenu(contenu);
            commentaire.setDate(LocalDate.now());
            
            commentaireRepository.save(commentaire);
            
            model.addAttribute("success", "Commentaire ajoute avec succes");
        }

        return "redirect:/encadrant/commenter";
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("encadrantConnecte");
        return "redirect:/";
    }
 // Voir details d'un stagiaire
    @GetMapping("/stagiaire/{id}")
    public String voirDetailsStagiaire(@PathVariable Long id, 
                                       Model model, 
                                       HttpSession session) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        Stagiaire stagiaire = stagiaireService.findById(id).orElse(null);
        if (stagiaire == null) {
            return "redirect:/encadrant/stagiaires";
        }

        List<Commentaire> commentaires = commentaireRepository.findByStagiaire(stagiaire);

        model.addAttribute("stagiaire", stagiaire);
        model.addAttribute("commentaires", commentaires);
        return "encadrant-stagiaire-detail";
    }

    // Valider le rapport d'un stagiaire
    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/valider-rapport/{id}")
    public String validerRapport(@PathVariable Long id, 
                                 HttpSession session,
                                 Model model) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        Stagiaire stagiaire = stagiaireService.findById(id).orElse(null);
        if (stagiaire != null) {
            stagiaire.setRapportValide(true);
            stagiaireService.save(stagiaire);
            
            // ✅ CRÉER NOTIFICATION POUR CCI
            Notification notif = new Notification(
                "VALIDATION_RAPPORT",
                "Le rapport de " + stagiaire.getPrenom() + " " + stagiaire.getNom() + 
                " a ete valide par " + encadrant.getNom(),
                stagiaire
            );
            notificationRepository.save(notif);
            
            model.addAttribute("success", "Rapport valide - Notification envoyee au CCI");
        }

        return "redirect:/encadrant/stagiaire/" + id;
    }

    // Commenter depuis la page de details
    @PostMapping("/commenter-stagiaire")
    public String commenterStagiaire(@RequestParam Long stagiaireId,
                                     @RequestParam String contenu,
                                     HttpSession session,
                                     Model model) {
        Encadrant encadrant = (Encadrant) session.getAttribute("encadrantConnecte");
        if (encadrant == null) {
            return "redirect:/encadrant/connexion";
        }

        encadrant = encadrantService.findById(encadrant.getId()).orElse(null);
        Stagiaire stagiaire = stagiaireService.findById(stagiaireId).orElse(null);
        
        if (stagiaire != null && encadrant != null) {
            Commentaire commentaire = new Commentaire();
            commentaire.setEncadrant(encadrant);
            commentaire.setStagiaire(stagiaire);
            commentaire.setContenu(contenu);
            commentaire.setDate(LocalDate.now());
            
            commentaireRepository.save(commentaire);
            
            model.addAttribute("success", "Commentaire ajoute");
        }

        return "redirect:/encadrant/stagiaire/" + stagiaireId;
    }
}

