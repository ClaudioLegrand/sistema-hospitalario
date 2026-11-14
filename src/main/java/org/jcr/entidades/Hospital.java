package org.jcr.entidades;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final List<Departamento> departamentos;
    private final List<Paciente> pacientes;

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre del hospital es obligatorio");
        this.direccion = Validaciones.textoObligatorio(direccion, "La dirección del hospital es obligatoria");
        this.telefono = Validaciones.textoObligatorio(telefono, "El teléfono del hospital es obligatorio");
        this.departamentos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento departamento) {
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo");

        // Delega en la otra punta para que un solo método actualice las dos listas
        departamento.setHospital(this);
    }

    public void agregarPaciente(Paciente paciente) {
        Validaciones.noNulo(paciente, "El paciente no puede ser nulo");

        // Delega en la otra punta para que un solo método actualice las dos listas
        paciente.setHospital(this);
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }

    // Visibilidad de paquete devuelve la lista real para sincronización interna
    List<Departamento> getInternalDepartamentos() {
        return departamentos;
    }

    // Visibilidad de paquete devuelve la lista real para sincronización interna
    List<Paciente> getInternalPacientes() {
        return pacientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}