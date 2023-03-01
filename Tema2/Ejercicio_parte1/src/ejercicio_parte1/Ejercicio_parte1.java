package ejercicio_parte1;

import java.awt.SystemColor;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author josea
 */
public class Ejercicio_parte1 {

    public static void main(String[] args) {

        String nombre;
        boolean resultado;

        // El email a validar
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("Introduce datos de alumnos: \n"
                    + "Escribe un nombre:");
            nombre = sc.nextLine();
            // Patrón para validar el email
            Pattern pattern = Pattern.compile("^[_A-Za-z]");
            Matcher mather = pattern.matcher(nombre);

            if (mather.find() == true) {
                resultado = false;
                //System.out.println("El email ingresado es válido.");
                //return resultado;
            } else {
                resultado = true;
                System.out.println("El NOMBRE ingresado es NO es válido.");

            }
       

       
            System.out.println("Introduce la EDAD entre 1 y 90: \n");
            String edad = sc.nextLine();

            resultado = (edad != null && edad.matches("[0-9]+"));
            if (resultado!=true){
                System.out.println("Edad incorrecta");
            }
           } while (resultado);
    }

}
