package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

public class Medico extends Persona {
    
    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();
    
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
        return Collections.unmodifiableList(citas); //mostrar copia de citas del medico
    }

    public void setDepartamento(Departamento departamento) {
        if (this.departamento != departamento) { // Primero se valida, si falla, ninguna lista queda modificada
            if (departamento != null && departamento.getEspecialidad() != especialidad) {
                throw new IllegalArgumentException("El médico solo puede estar en un departamento de su especialidad");
            }
            if (this.departamento != null) { // Si tenía un departamento anterior, el médico se borra de su lista
                this.departamento.getInternalMedicos().remove(this);
            }
            this.departamento = departamento; // Se guarda la nueva referencia en el médico
            if (departamento != null) { // Si el departamento pasado por parametro (el nuevo) no es null, el médico entra en su lista, si es null =  se queda sin departamento
                departamento.getInternalMedicos().add(this);
            }
        }
    }
    
    public void addCita(Cita cita){
        Validaciones.noNulo(cita, "La cita es obligatoria");
        citas.add(cita); //agregar las citas
    }
    
    
    
    
    
    

}
