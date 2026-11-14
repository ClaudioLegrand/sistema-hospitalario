package org.jcr.entidades;

import java.util.ArrayList;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;

public class Departamento {

    private String nombre;
    private EspecialidadMedica especialidad;
    private Hospital hospital;
    private List<Medico> medicos;
    private List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null){

            this.nombre = nombre;
        }
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadMedica especialidad) {
        if (especialidad != null){

            this.especialidad = especialidad;
        }
    }

    public Hospital getHospital() {
        return hospital;
    }
    
    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {
            this.hospital = hospital;
            
            
            if (hospital != null && !hospital.getDepartamentos().contains(this)) {
                hospital.agregarDepartamento(this);
            }
        }
    }

    public void agregarMedico(Medico medico) {
        if (medico != null && !this.medicos.contains(medico)) {
            this.medicos.add(medico);

        }
    }

    public Sala crearSala(String numero, String tipo) {

        Sala nuevaSala = new Sala(numero, tipo, this);
    
        if (!this.salas.contains(nuevaSala)) {
            this.salas.add(nuevaSala);
        }
        
        return nuevaSala;
    }

    public List<Medico> getMedicos() {
        return new ArrayList<>(medicos);
    }

    public List<Sala> getSalas() {
        return new ArrayList<>(salas);
    }

    List<Medico> getInternalMedicos() {
        return medicos;
    }
}
