package com.ocp.stage.suivistagiaires.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocp.stage.suivistagiaires.model.Document;
import com.ocp.stage.suivistagiaires.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public Document create(@RequestBody Document document) {
        return documentService.save(document);
    }

    @GetMapping
    public List<Document> getAll() {
        return documentService.findAll();
    }

    @GetMapping("/{id}")
    public Document getById(@PathVariable Long id) {
        return documentService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        documentService.deleteById(id);
    }
}
