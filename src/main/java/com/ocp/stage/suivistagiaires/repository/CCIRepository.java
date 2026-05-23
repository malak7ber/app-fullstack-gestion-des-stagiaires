package com.ocp.stage.suivistagiaires.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocp.stage.suivistagiaires.model.CCI;

public interface CCIRepository extends JpaRepository<CCI, Long> {

    Optional<CCI> findByEmail(String email);
}
