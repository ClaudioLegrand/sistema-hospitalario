package org.jcr.entidades;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private String numero;
    private String tipo;
    private Departamento departamento;
    private List<Cita> citas;
    
    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = numero;
        this.tipo = tipo;
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
        if (cita == null) {
            return;
        }
        
        if (!this.citas.contains(cita)) {
            this.citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas); 
    }
}