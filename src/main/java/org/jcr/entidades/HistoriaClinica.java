package org.jcr.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoriaClinica {

    private String numeroHistoria;
    private Paciente paciente;
    private LocalDateTime fechaCreacion;
    private List<String> diagnosticos;
    private List<String> tratamientos;
    private List<String> alergias;

    HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria();
        this.diagnosticos = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
        this.alergias = new ArrayList<>();
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void agregarDiagnostico(String diagnostico) {
        
        if (diagnostico != null){
        
            this.diagnosticos.add(diagnostico);
        }
    }

    public void agregarTratamiento(String tratamiento) {
        
        if (tratamiento != null){
            
            this.tratamientos.add(tratamiento);
        }
    }

    public void agregarAlergia(String alergia) {
        
        if (alergia != null){
            
            this.alergias.add(alergia);
        }
        
    }

    public List<String> getDiagnosticos() {
        return new ArrayList<>(diagnosticos);
    }

    public List<String> getTratamientos() {
        return new ArrayList<>(tratamientos);
    }

    public List<String> getAlergias() {
        return new ArrayList<>(alergias);
    }

    private String generarNumeroHistoria() {
        
        return "HC-" + paciente.getDni() + "-" + fechaCreacion.getYear();
    }
}
