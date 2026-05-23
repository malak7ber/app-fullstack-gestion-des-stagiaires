package com.ocp.stage.suivistagiaires.controller;

import com.ocp.stage.suivistagiaires.model.Stagiaire;
import com.ocp.stage.suivistagiaires.service.AttestationPdfService;
import com.ocp.stage.suivistagiaires.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attestation")
public class AttestationController {

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private AttestationPdfService pdfService;

    @GetMapping("/generer/{stagiaireId}")
    public ResponseEntity<byte[]> genererAttestation(@PathVariable Long stagiaireId) {
        try {
            Stagiaire stagiaire = stagiaireService.findById(stagiaireId).orElse(null);
            
            if (stagiaire == null) {
                return ResponseEntity.notFound().build();
            }

            if (!stagiaire.isValide()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            byte[] htmlBytes = pdfService.genererAttestation(stagiaire);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            String filename = "Attestation_" + stagiaire.getNom() + "_" + stagiaire.getPrenom() + ".html";
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(htmlBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/preview/{stagiaireId}")
    public ResponseEntity<byte[]> previewAttestation(@PathVariable Long stagiaireId) {
        try {
            Stagiaire stagiaire = stagiaireService.findById(stagiaireId).orElse(null);
            
            if (stagiaire == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] htmlBytes = pdfService.genererAttestation(stagiaire);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("inline", "attestation.html");

            return new ResponseEntity<>(htmlBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}