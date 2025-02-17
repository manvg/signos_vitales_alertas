package com.alertas.signos_vitales_alertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alertas.signos_vitales_alertas.model.AlertaMedica;

@Repository
public interface AlertaMedicaRepository extends JpaRepository<AlertaMedica, Integer> {
    
}
