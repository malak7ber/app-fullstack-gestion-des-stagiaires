package com.ocp.stage.suivistagiaires.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.Document;
import com.ocp.stage.suivistagiaires.model.TypeDoc;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByStagiaire_Id(Long stagiaireId);
    List<Document> findByType(TypeDoc type);
}
 