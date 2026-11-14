package org.jcr.entidades;


final class Validaciones {

    private Validaciones() {
    }
    
    //Un texto obligatorio es nulo, vacío o tiene solo espacios
    
    static String textoObligatorio(String valor, String mensajeError){
        if (valor == null || valor.trim().isEmpty()){ // si valor es nulo y el esta vacio o tiene solo espacios.
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }
    
    static void noNulo(Object valor, String mensajeError){
        if (valor == null){
            throw new IllegalArgumentException(mensajeError);
        }
    }
    
    static boolean sonDigitos(String texto){

        // LO DEJAMOS?
        if (texto == null || texto.isEmpty()) {
            return false;
        }

        for (int i = 0; i < texto.length(); i++) {
            char caracter= texto.charAt(i);
            if (!Character.isDigit(caracter)){
                return false;
            }
        }
        return true;
                
    }
}
            