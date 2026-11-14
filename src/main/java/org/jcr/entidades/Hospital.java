package org.jcr.entidades;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private String nombre;
    private String direccion;
    private String telefono;
    private List<Departamento> departamentos;
    private List<Paciente> pacientes;

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.departamentos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }
    
    public void agregarDepartamento(Departamento departamento) {
        
        boolean noNull = departamento != null;
        
        // Si el departamento no tiene este hospital asignado, se lo asignamos
        if (noNull && departamento.getHospital() != this) {
            departamento.setHospital(this);
        }

        // Si no esta dentro de la lista se agrega 
        if (noNull && !this.departamentos.contains(departamento)) {
            this.departamentos.add(departamento);
        }
    }

    public void agregarPaciente(Paciente paciente) {
        
        boolean noNull = paciente != null;
        
        if (noNull && paciente.getHospital() != this) {
            paciente.setHospital(this);
        }

        if (noNull && !this.pacientes.contains(paciente)) {
            this.pacientes.add(paciente);
        }
    }

    
    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(departamentos); // Pasamos una copia por seguridad
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(pacientes); // Pasamos una copia por seguridad
    }

    // Visibilidad en paquete
    List<Departamento> getInternalDepartamentos() {
        return departamentos; // Referencia directa
    }

    // Visibilidad en paquete
    List<Paciente> getInternalPacientes() {
        return pacientes; // Referencia directa
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
