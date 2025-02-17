package com.alertas.signos_vitales_alertas.service;

import org.springframework.stereotype.Service;
import com.alertas.signos_vitales_alertas.model.AlertaMedica;
import com.alertas.signos_vitales_alertas.repository.AlertaMedicaRepository;

@Service
public class AlertaMedicaService {

    private final AlertaMedicaRepository alertaMedicaRepository;

    public AlertaMedicaService(AlertaMedicaRepository alertaMedicaRepository) {
        this.alertaMedicaRepository = alertaMedicaRepository;
    }

    public void guardarAlerta(AlertaMedica alertaRecibida) {
        AlertaMedica nuevaAlerta = new AlertaMedica(
            alertaRecibida.getNombrePaciente(),
            alertaRecibida.getRitmoCardiaco(),
            alertaRecibida.getTemperatura(),
            alertaRecibida.getPresionSistolica(),
            alertaRecibida.getPresionDiastolica(),
            alertaRecibida.getObservacion()
        );

        alertaMedicaRepository.save(nuevaAlerta);

        System.out.println("Alerta médica guardada en la base de datos: " + nuevaAlerta);
    }
}
