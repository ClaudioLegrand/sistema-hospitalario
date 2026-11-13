package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jcr.enums.TipoSangre;

public class Paciente extends Persona { 

    private HistoriaClinica historiaClinica;
    private String telefono;
    private String direccion;
    private Hospital hospital;
    private List<Cita> citas;

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.telefono = telefono;
        this.direccion = direccion;
        this.historiaClinica = new HistoriaClinica(this);
        this.citas = new ArrayList<>();
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {
            this.hospital = hospital;

            if (hospital != null && !hospital.getPacientes().contains(this)) {
                hospital.agregarPaciente(this);
            }
        }
    }

    public void addCita(Cita cita) {
        
        boolean noNull = cita != null;
        
        if (noNull && cita.getPaciente() != this){
        
            cita.setPaciente(this);
        }
        
        if (noNull && !this.citas.contains(cita)){
            this.citas.add(cita);
        }
        
    }

    public List<Cita> getCitas() {
        return citas;
    }
}