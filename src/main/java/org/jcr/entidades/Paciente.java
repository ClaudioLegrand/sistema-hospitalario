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
            // Salir de la lista del hospital anterior (si estaba en uno)
            if (this.hospital != null) {
                this.hospital.getInternalPacientes().remove(this);
            }
            // Guardar la nueva referencia
            this.hospital = hospital;
            // Entrar en la lista del nuevo hospital (si no es null)
            if (hospital != null && !hospital.getInternalPacientes().contains(this)) {
                hospital.getInternalPacientes().add(this);
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