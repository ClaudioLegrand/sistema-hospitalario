package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;

public class Departamento {

    private final String nombre;
    private final EspecialidadMedica especialidad;
    private Hospital hospital;
    private final List<Medico> medicos;
    private final List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre del departamento es obligatorio");
        Validaciones.noNulo(especialidad, "La especialidad médica es obligatoria");
        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {

            // Salir de la lista del hospital anterior (si estaba en uno)
            if (this.hospital != null) {
                this.hospital.getInternalDepartamentos().remove(this);
            }
            
            // Guardar la nueva referencia
            this.hospital = hospital;

            // Entrar en la lista del nuevo hospital (si no es null y no está duplicado)
            if (hospital != null && !hospital.getInternalDepartamentos().contains(this)) {
                hospital.getInternalDepartamentos().add(this);
            }
        }
    }

    public void agregarMedico(Medico medico) {
        Validaciones.noNulo(medico, "El médico no puede ser nulo");

        // Delega en Medico 
        medico.setDepartamento(this);
    }

    public Sala crearSala(String numero, String tipo) {
        Validaciones.textoObligatorio(numero, "El número de sala es obligatorio");
        Validaciones.textoObligatorio(tipo, "El tipo de sala es obligatorio");

        Sala nuevaSala = new Sala(numero, tipo, this);
        this.salas.add(nuevaSala);
        return nuevaSala;
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(salas);
    }

    // Visibilidad de paquete
    List<Medico> getInternalMedicos() {
        return medicos;
    }
}