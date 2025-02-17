package com.alertas.signos_vitales_alertas.service;

import com.alertas.signos_vitales_alertas.model.AlertaMedica;
import com.alertas.signos_vitales_alertas.repository.AlertaMedicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaAlertaMedicaService {

    @Autowired
    private AlertaMedicaRepository alertaMedicaRepository;

    private final ObjectMapper objectMapper;

    public KafkaAlertaMedicaService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Manejo de LocalDateTime
    }

    @KafkaListener(topics = "alertas", groupId = "grupo-alertas")
    public void consumirMensaje(String mensaje) {
        try {
            AlertaMedica alerta = objectMapper.readValue(mensaje, AlertaMedica.class);
            alertaMedicaRepository.save(alerta);
            System.out.println("Alerta médica guardada en base de datos: " + alerta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}