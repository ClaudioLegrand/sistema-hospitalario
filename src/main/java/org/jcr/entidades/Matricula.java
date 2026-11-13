package org.jcr.entidades;

public class Matricula {
    
    private static final String PREFIJO = "MP-";
    private final String numero;

    public Matricula(String numero) {
        this.numero = validarMatricula(numero);
    }

    public String getNumero() {
        return numero;
    }

    private static String validarMatricula(String numero) {
        Validaciones.textoObligatorio(numero, "El número de matrícula es obligatorio");
        if (numero.startsWith(PREFIJO)) {
            //numero deberia arrancar a contar desde dps de mp [posición 3] Y SI EL DIA DE MANANA CAMBIA?
            String digitos = ""; //contar los caracteres en char de prefijos [por si mañana cambian] y luego de eso sumar hasta el string con su char final.
            for (int i = PREFIJO.length(); i < numero.length(); i++) { //i = a la longitud del prefijo, va a ir hasta el ultimo, luego a digitos se le suman y se suman los char a digitos
                digitos = digitos + numero.charAt(i);
            }
        if (digitos.length() < 4 || digitos.length() > 6 || !Validaciones.sonDigitos(digitos)) {
            throw new IllegalArgumentException("La matrícula debe ser MP- seguido de 4 a 6 dígitos");
        }
        return numero;
        }
    }
     

}
