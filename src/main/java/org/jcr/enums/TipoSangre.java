package org.jcr.enums;

public enum TipoSangre {

    A_POSITIVO, A_NEGATIVO, O_NEGATIVO, O_POSITIVO, B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO;

    private String descripcion;
    
    TipoSangre() {
        this.descripcion = this.name();
    }

    public String getDescripcion() {
        return descripcion;
    }

}
