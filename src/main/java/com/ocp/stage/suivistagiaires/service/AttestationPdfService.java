package com.ocp.stage.suivistagiaires.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.Stagiaire;

/**
 * Service pour générer les attestations de stage
 * VERSION SIMPLE sans dépendance iText - génère du HTML
 */
@Service
public class AttestationPdfService {

	public byte[] genererAttestation(Stagiaire stagiaire) throws Exception {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
	    String dateDebutStr = stagiaire.getDateDebut() != null ? 
	        stagiaire.getDateDebut().format(formatter) : "N/A";
	    String dateFinStr = stagiaire.getDateFin() != null ? 
	        stagiaire.getDateFin().format(formatter) : "N/A";
	    String dateActuelle = LocalDate.now().format(formatter);

	    String html = "<!DOCTYPE html>\n" +
	        "<html>\n" +
	        "<head>\n" +
	        "    <meta charset='UTF-8'>\n" +
	        "    <style>\n" +
	        "        @page { margin: 2cm; }\n" +
	        "        body { font-family: 'Times New Roman', serif; margin: 0; padding: 40px; line-height: 1.8; }\n" +
	        "        .header { text-align: center; border-bottom: 3px solid #0052cc; padding-bottom: 20px; margin-bottom: 40px; }\n" +
	        "        .logo { color: #0052cc; font-size: 32px; font-weight: bold; }\n" +
	        "        .subtitle { color: #666; font-size: 14px; font-style: italic; margin-top: 5px; }\n" +
	        "        .title { text-align: center; font-size: 28px; font-weight: bold; margin: 50px 0 40px 0; text-decoration: underline; text-transform: uppercase; }\n" +
	        "        .content { text-align: justify; font-size: 14px; line-height: 2; margin: 30px 0; }\n" +
	        "        .signature { margin-top: 80px; float: right; text-align: center; }\n" +
	        "        .signature-line { width: 200px; border-top: 1px solid #000; margin-top: 60px; }\n" +
	        "        .footer { position: fixed; bottom: 20px; left: 0; right: 0; text-align: center; font-size: 10px; color: #666; border-top: 1px solid #ddd; padding-top: 10px; }\n" +
	        "        .reference { font-size: 11px; color: #666; margin-bottom: 20px; }\n" +
	        "    </style>\n" +
	        "</head>\n" +
	        "<body>\n" +
	        "    <div class='header'>\n" +
	        "        <div class='logo'>OFFICE CHERIFIEN DES PHOSPHATES</div>\n" +
	        "        <div class='subtitle'>Groupe OCP - Centre des Competences Intellectuelles</div>\n" +
	        "    </div>\n" +
	        "    \n" +
	        "    <div class='reference'>Ref: OCP/CCI/" + LocalDate.now().getYear() + "/" + 
	        String.format("%04d", stagiaire.getId()) + "</div>\n" +
	        "    \n" +
	        "    <div class='title'>Attestation de Stage</div>\n" +
	        "    \n" +
	        "    <div class='content'>\n" +
	        "        <p>Le soussigne, Directeur du Centre des Competences Intellectuelles de l'Office Cherifien des Phosphates, atteste que :</p>\n" +
	        "        \n" +
	        "        <p style='text-align: center; margin: 30px 0;'>\n" +
	        "            <strong style='font-size: 16px;'>" + 
	        (stagiaire.getPrenom().toLowerCase().startsWith("a") || 
	         stagiaire.getPrenom().toLowerCase().startsWith("e") || 
	         stagiaire.getPrenom().toLowerCase().startsWith("i") || 
	         stagiaire.getPrenom().toLowerCase().startsWith("o") || 
	         stagiaire.getPrenom().toLowerCase().startsWith("u") ? "Mme/M. " : "M./Mme ") +
	        stagiaire.getPrenom() + " " + stagiaire.getNom().toUpperCase() + 
	        "</strong>\n" +
	        "        </p>\n" +
	        "        \n" +
	        "        <p>Etudiant(e) a <strong>" + stagiaire.getEcole() + "</strong> en <strong>" + 
	        stagiaire.getSpecialite() + "</strong>, a effectue un stage au sein de nos services " +
	        "du <strong>" + dateDebutStr + "</strong> au <strong>" + dateFinStr + "</strong>.</p>\n";

	    if (stagiaire.getEncadrant() != null) {
	        html += "        <p>Durant cette periode, le/la stagiaire a ete encadre(e) par <strong>" + 
	            stagiaire.getEncadrant().getNom() + "</strong>, " + 
	            (stagiaire.getEncadrant().getSpecialite() != null ? stagiaire.getEncadrant().getSpecialite() : "cadre") + 
	            " au sein du " + 
	            (stagiaire.getEncadrant().getService() != null ? stagiaire.getEncadrant().getService() : "Groupe OCP") + 
	            ".</p>\n";
	    }

	    html += "        <p>Le/la stagiaire s'est montre(e) serieux(se), motive(e) et a fait preuve de competences " +
	        "professionnelles conformes aux attentes de notre organisation.</p>\n" +
	        "        \n" +
	        "        <p>Cette attestation est delivree a l'interesse(e) pour servir et valoir ce que de droit.</p>\n" +
	        "    </div>\n" +
	        "    \n" +
	        "    <div class='signature'>\n" +
	        "        <p>Fait a Casablanca, le " + dateActuelle + "</p>\n" +
	        "        <div class='signature-line'></div>\n" +
	        "        <p><strong>Le Directeur du CCI</strong></p>\n" +
	        "    </div>\n" +
	        "    \n" +
	        "    <div class='footer'>\n" +
	        "        Office Cherifien des Phosphates - Siege Social: 2-4 Rue Al Abtal, Hay Erraha, 20200 Casablanca, Maroc<br>\n" +
	        "        Tel: +212 5XX XX XX XX | www.ocpgroup.ma\n" +
	        "    </div>\n" +
	        "</body>\n" +
	        "</html>";

	    return html.getBytes(StandardCharsets.UTF_8);
	}
	}