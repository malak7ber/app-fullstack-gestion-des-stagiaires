package com.ocp.stage.suivistagiaires.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.CongéEncadrant;

public interface CongéEncadrantRepository extends JpaRepository<CongéEncadrant, Long> {

    List<CongéEncadrant> findByEncadrant_Id(Long encadrantId);
}
