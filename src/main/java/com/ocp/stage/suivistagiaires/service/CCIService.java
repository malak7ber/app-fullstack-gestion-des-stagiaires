package com.ocp.stage.suivistagiaires.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocp.stage.suivistagiaires.model.CCI;
import com.ocp.stage.suivistagiaires.repository.CCIRepository;

@Service
public class CCIService {

    private final CCIRepository cciRepository;

    @Autowired
    public CCIService(CCIRepository cciRepository) {
        this.cciRepository = cciRepository;
    }

    public CCI save(CCI cci) {
        return cciRepository.save(cci);
    }

    public List<CCI> findAll() {
        return cciRepository.findAll();
    }

    public Optional<CCI> findById(Long id) {
        return cciRepository.findById(id);
    }

    public void deleteById(Long id) {
        cciRepository.deleteById(id);
    }
}
