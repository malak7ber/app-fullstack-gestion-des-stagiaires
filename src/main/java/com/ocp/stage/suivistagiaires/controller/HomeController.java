package com.ocp.stage.suivistagiaires.controller;

import com.ocp.stage.suivistagiaires.model.Commentaire;
import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.repository.CommentaireRepository;
import com.ocp.stage.suivistagiaires.service.StagiaireService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/stagiaire")
    public String stagiaireHome() {
        return "stagiaire-home";
    }

    @GetMapping("/stagiaire/demande-stage")
    public String showDemandeStageForm(Model model) {
        model.addAttribute("stagiaire", new Stagiaire());
        return "demande-stage";
    }

    @PostMapping("/stagiaire/demande-stage")
    public String submitDemandeStage(@ModelAttribute("stagiaire") Stagiaire stagiaire, Model model) {
        try {
            stagiaireService.save(stagiaire);
            return "redirect:/stagiaire/success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Erreur lors de la soumission : " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/stagiaire/success")
    public String showSuccessPage() {
        return "succes";
    }

    @GetMapping("/stagiaire/connexion")
    public String showLoginForm() {
        return "login-stagiaire";
    }

    @PostMapping("/stagiaire/login")
    public String loginStagiaire(@RequestParam String email,
                                 @RequestParam String motDePasse,
                                 Model model,
                                 HttpSession session) {
        Stagiaire stagiaire = stagiaireService.findByEmailAndMotDePasse(email, motDePasse);
        
        if (stagiaire == null) {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login-stagiaire";
        }

        if (!stagiaire.isValide()) {
            model.addAttribute("error", "Votre demande n'a pas encore ete validee.");
            return "login-stagiaire";
        }

        stagiaire = stagiaireService.findById(stagiaire.getId()).orElse(stagiaire);
        
        session.setAttribute("stagiaireConnecte", stagiaire);
        return "redirect:/stagiaire/dashboard";
    }

    @GetMapping("/stagiaire/dashboard")
    public String stagiaireDashboard(Model model, HttpSession session) {
        Stagiaire stagiaire = (Stagiaire) session.getAttribute("stagiaireConnecte");
        if (stagiaire == null) {
            return "redirect:/stagiaire/connexion";
        }
        
        stagiaire = stagiaireService.findById(stagiaire.getId()).orElse(stagiaire);
        
        model.addAttribute("stagiaire", stagiaire);
        return "dashboard-stagiaire";
    }

    @GetMapping("/stagiaire/deposer-rapport")
    public String showDeposerRapportForm() {
        return "deposer-rapport";
    }

    @PostMapping("/stagiaire/deposer-rapport")
    public String uploadRapport(@RequestParam("rapport") MultipartFile file, Model model) {
        try {
            Path chemin = Paths.get("src/main/resources/static/documents/" + file.getOriginalFilename());
            Files.write(chemin, file.getBytes());
            model.addAttribute("message", "Rapport envoye avec succes !");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Erreur lors de l'envoi du fichier.");
        }
        return "redirect:/stagiaire/dashboard";
    }

    @GetMapping("/stagiaire/commentaires")
    public String voirCommentaires(Model model, HttpSession session) {
        Stagiaire stagiaire = (Stagiaire) session.getAttribute("stagiaireConnecte");

        if (stagiaire == null) {
            return "redirect:/stagiaire/connexion";
        }

        stagiaire = stagiaireService.findById(stagiaire.getId()).orElse(stagiaire);
        List<Commentaire> commentaires = commentaireRepository.findByStagiaire(stagiaire);
        
        model.addAttribute("commentaires", commentaires);
        return "commentaires";
    }
    @PostMapping("/stagiaire/deposer-document")
    public String deposerDocument(@RequestParam("fichier") MultipartFile file,
                                  @RequestParam("typeDocument") String typeDocument,
                                  HttpSession session,
                                  Model model) {
        Stagiaire stagiaire = (Stagiaire) session.getAttribute("stagiaireConnecte");
        if (stagiaire == null) {
            return "redirect:/stagiaire/connexion";
        }

        try {
            String dossier = "src/main/resources/static/documents/stagiaires/" + stagiaire.getId() + "/";
            Path cheminDossier = Paths.get(dossier);
            if (!Files.exists(cheminDossier)) {
                Files.createDirectories(cheminDossier);
            }
            
            String nomFichier = typeDocument + "_" + file.getOriginalFilename();
            Path chemin = Paths.get(dossier + nomFichier);
            Files.write(chemin, file.getBytes());
            
            model.addAttribute("success", "Document envoye avec succes !");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Erreur lors de l'envoi");
        }
        
        return "redirect:/stagiaire/dashboard";
    }
    @PostMapping("/stagiaire/repondre-commentaire")
    public String repondreCommentaire(@RequestParam Long commentaireId,
                                      @RequestParam String reponse,
                                      HttpSession session,
                                      Model model) {
        Stagiaire stagiaire = (Stagiaire) session.getAttribute("stagiaireConnecte");
        if (stagiaire == null) {
            return "redirect:/stagiaire/connexion";
        }

        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElse(null);
        if (commentaire != null) {
            commentaire.setReponse(reponse);
            commentaire.setDateReponse(LocalDate.now());
            commentaireRepository.save(commentaire);
            
            model.addAttribute("success", "Reponse envoyee avec succes");
        }

        return "redirect:/stagiaire/commentaires";
    }
}
