package com.ocp.stage.suivistagiaires.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        
        // Deconnecter encadrant si il va vers CCI ou Stagiaire
        if (session.getAttribute("encadrantConnecte") != null) {
            if (requestURI.startsWith("/cci") || requestURI.startsWith("/stagiaire/")) {
                session.removeAttribute("encadrantConnecte");
            }
        }
        
        // Deconnecter stagiaire si il va vers CCI ou Encadrant
        if (session.getAttribute("stagiaireConnecte") != null) {
            if (requestURI.startsWith("/cci") || requestURI.startsWith("/encadrant")) {
                session.removeAttribute("stagiaireConnecte");
            }
        }
        
        // Deconnecter CCI si il va vers Encadrant ou Stagiaire
        if (session.getAttribute("cciConnecte") != null) {
            if (requestURI.startsWith("/encadrant") || requestURI.startsWith("/stagiaire/dashboard")) {
                session.removeAttribute("cciConnecte");
            }
        }
        
        return true;
    }
}