package org.jcr.entidades;

import java.time.LocalDateTime;
import org.jcr.enums.EstadoCita;

public class Cita {

    private Paciente paciente;
    private Medico medico;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double costo;
    private EstadoCita estado; 
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) {
        Validaciones.noNulo(paciente, "El paciente es obligatorio");
        Validaciones.noNulo(medico, "El médico es obligatorio");
        Validaciones.noNulo(sala, "La sala es obligatoria");
        Validaciones.noNulo(fechaHora, "La fecha y hora son obligatorias");
        Validaciones.noNulo(costo, "El costo es obligatorio");
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo; //QUE PASA SI EL COSTO ES NEGATIVO? VALIDAR, tiene que ser digito, no ser nulo y a su vez no negativo.
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones = "";
        this.paciente.addCita(this); //agregar la cita al paciente
        this.medico.addCita(this); //agregar la cita al medico 
        this.sala.addCita(this); //agregar la cita a la sala
        
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) { // poner como programada
        Validaciones.noNulo(estado, "El estado de la cita es obligatorio");
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones; //validar?
    }
    
}
