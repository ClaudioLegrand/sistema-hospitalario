package org.jcr.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

public class CitaManager implements CitaService {

    private static final int HORAS_ENTRE_CITAS = 2;
    private List<Cita> citas;

    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null || medico == null || sala == null) {
            throw new CitaException("El paciente, el médico y la sala son obligatorios");
        }
        validarCita(fechaHora, costo);
        if (medico.getEspecialidad() != sala.getDepartamento().getEspecialidad()) {
            throw new CitaException("El médico y la sala deben pertenecer a la misma especialidad");
        }
        if (!esMedicoDisponible(medico, fechaHora)) {
            throw new CitaException("El médico ya tiene otra cita a menos de " + HORAS_ENTRE_CITAS + " horas");
        }
        if (!esSalaDisponible(sala, fechaHora)) {
            throw new CitaException("La sala ya tiene otra cita a menos de " + HORAS_ENTRE_CITAS + " horas");
        }

        Cita cita = new Cita(paciente, medico, sala, fechaHora, costo);
        citas.add(cita);
        paciente.addCita(cita);
        medico.addCita(cita);
        sala.addCita(cita);
        return cita;
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente() == paciente) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMedico() == medico) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getSala() == sala) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    private void validarCita(LocalDateTime fechaHora, double costo) throws CitaException {
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La fecha de la cita es obligatoria y no puede ser pasada");
        }
        if (costo <= 0) {
            throw new CitaException("El costo de la cita debe ser mayor a 0");
        }
    }

    private boolean esMedicoDisponible(Medico medico, LocalDateTime fechaHora) {
        for (Cita existente : citas) {
            if (existente.getMedico() == medico && seSuperponen(existente.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean esSalaDisponible(Sala sala, LocalDateTime fechaHora) {
        for (Cita existente : citas) {
            if (existente.getSala() == sala && seSuperponen(existente.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean seSuperponen(LocalDateTime existente, LocalDateTime nueva) {
        return nueva.isAfter(existente.minusHours(HORAS_ENTRE_CITAS)) && nueva.isBefore(existente.plusHours(HORAS_ENTRE_CITAS));
    }

}
