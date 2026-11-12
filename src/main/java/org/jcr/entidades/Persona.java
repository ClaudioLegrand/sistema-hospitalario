package org.jcr.entidades;

import java.time.LocalDate;

import org.jcr.enums.TipoSangre;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        
        if (validarDni()) {
            this.dni = dni; //TODO: VERIFICAR SI SE CANCELA TODO EL CONSTRUCTOR
        }
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.getYear() - fechaNacimiento.getYear();
    }

    private boolean validarDni() {
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
        }
        return true;
    }

}
