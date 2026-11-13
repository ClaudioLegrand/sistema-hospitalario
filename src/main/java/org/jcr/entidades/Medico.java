package org.jcr.entidades;

import java.time.LocalDate;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

public class Medico extends Persona {
    
    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas;
    
    //Constructor

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        Validaciones.noNulo(especialidad, "La especialidad es obligatoria");
        this.especialidad = especialidad;
        this.matricula = new Matricula(numeroMatricula);
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public void addCita(Cita cita){
        citas.add(cita); //agregar las citas
    }
    
    
    
    
    
    

}
