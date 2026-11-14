package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {

    private final String numero;
    private final String tipo;
    private final Departamento departamento;
    private final List<Cita> citas;

    // Visibilidad de paquete solo Departamento la instancia mediante crearSala()
    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Validaciones.textoObligatorio(numero, "El número de sala es obligatorio");
        this.tipo = Validaciones.textoObligatorio(tipo, "El tipo de sala es obligatorio");
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo");
        this.departamento = departamento;
        this.citas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void addCita(Cita cita) {
        Validaciones.noNulo(cita, "La cita no puede ser nula");
        if (!this.citas.contains(cita)) {
            this.citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
}