package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.CongéEncadrant;
import com.ocp.stage.suivistagiaires.repository.CongéEncadrantRepository;

@Service
public class CongéEncadrantService {

    private final CongéEncadrantRepository congéRepository;

    @Autowired
    public CongéEncadrantService(CongéEncadrantRepository congéRepository) {
        this.congéRepository = congéRepository;
    }

    public CongéEncadrant save(CongéEncadrant congé) {
        return congéRepository.save(congé);
    }

    public List<CongéEncadrant> findAll() {
        return congéRepository.findAll();
    }

    public Optional<CongéEncadrant> findById(Long id) {
        return congéRepository.findById(id);
    }

    public void deleteById(Long id) {
        congéRepository.deleteById(id);
    }
}
