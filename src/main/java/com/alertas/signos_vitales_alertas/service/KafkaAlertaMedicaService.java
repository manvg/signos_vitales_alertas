package com.alertas.signos_vitales_alertas.service;

import com.alertas.signos_vitales_alertas.model.AlertaMedica;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaAlertaMedicaService {

    private final AlertaMedicaService alertaMedicaService;
    private final ObjectMapper objectMapper;

    public KafkaAlertaMedicaService(AlertaMedicaService alertaMedicaService) {
        this.alertaMedicaService = alertaMedicaService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Manejo de LocalDateTime
    }

    @KafkaListener(topics = "alertas", groupId = "grupo-alertas")
    public void consumirMensaje(String mensaje) {
        try {
            AlertaMedica alerta = objectMapper.readValue(mensaje, AlertaMedica.class);
            
            alertaMedicaService.guardarAlerta(alerta);
            
            System.out.println("Alerta médica guardada en la base de datos: " + alerta);
        } catch (Exception e) {
            System.err.println("Error al procesar alerta médica: " + e.getMessage());
            e.printStackTrace();
        }
    }
}