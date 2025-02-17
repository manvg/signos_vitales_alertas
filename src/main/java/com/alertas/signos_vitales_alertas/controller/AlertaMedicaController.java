package com.alertas.signos_vitales_alertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alertas.signos_vitales_alertas.model.AlertaMedica;
import com.alertas.signos_vitales_alertas.repository.AlertaMedicaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaMedicaController {

    @Autowired
    private AlertaMedicaRepository alertaMedicaRepository;

    @GetMapping
    public List<AlertaMedica> obtenerAlertas() {
        return alertaMedicaRepository.findAll();
    }

    @GetMapping("/ping")
    public String verificarConexion() {
        return "Microservicio de Alerta en ejecución";
    }
}