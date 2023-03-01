/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colaborar;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class Colaborar {

    
    /**
     * La aplicación ordena a la aplicación lenguaje que cree 500 lineas de letras aleatorias.
     * Para esto manda 10 veces a la plicación lenguaje la ejecución aumentando de 10 en 10 las lineas a escribir.
     * @param args el argumento se tienen que introducir el la linea de comandos siendo el nombre del fichero a crear para la grabación de los datos
     * 
     */
    public static void main(String[] args) {
        if (args.length == 1) {

            try {
                for (int i = 1; i <= 10; i++) {
                    //llamada a proceso de 10 en 10
                    System.out.println("Iniciado proceso numero:" + i);

                    String comando = "java -jar Lenguaje.jar " + (i * 10) + " " + args[0];//sentencia para iniciar el proceso lenguaje pasando por argumento el nombre del fichero a crear
                    System.out.println("Sentencia iniciada." + comando);
                    Runtime.getRuntime().exec(comando);//ejecutamos el comando

                }
            } catch (IOException ex) {
                Logger.getLogger(Colaborar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("falta el nombre del archivo");
        }
    }

}
