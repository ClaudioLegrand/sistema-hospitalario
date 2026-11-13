package org.jcr.entidades;

import java.time.LocalDate;

import org.jcr.enums.TipoSangre;

public abstract class Persona {

    protected String nombre;
    protected String apellido;
    protected String dni;
    protected LocalDate fechaNacimiento;
    protected TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        Validaciones.noNulo(fechaNacimiento, "La fecha de nacimiento es obligatoria");
        Validaciones.noNulo(tipoSangre, "El tipo de sangre es obligatorio");
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre es obligatorio");
        this.apellido = Validaciones.textoObligatorio(apellido, "El apellido es obligatorio");
        this.dni = validarDni(dni);
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
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();
        /* si hoy es 13/12/2026 y cumple mañana -> deberia tener 27, calculo = 
        (14/11/1998 + 28(por diferencia entre años = 14/11/2026, ahora si fecha 
        es despues de la fecha de hoy, se le resta un año, si no, edad es la diferencia anterior ya calculada ))
        */
        if (fechaNacimiento.plusYears(edad).isAfter(fechaActual)) { 
            edad = edad -1;
        }
        return edad; // si no, devuelve edad ya calculada
    }

    private static String validarDni(String dni) { 
        /*privado: solo se usa dentro de esta clase y el static por que el metodo pertenece a la clase en general y un a un obj,
        se puede llmaar directamente sin instanciar la clase persona.*/
        Validaciones.textoObligatorio(dni, "El DNI es obligatorio"); // primer validación.
        if (dni.length() < 7 || dni.length() > 8 || !Validaciones.sonDigitos(dni)) { //segunda validación.
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 caracteres y todos deben ser dígitos");
        }
        return dni;
    }

}
