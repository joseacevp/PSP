/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecalendario;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class IniciadorDeCliente {
    public void llamar (String ruta){
        if (ruta!=null) {

            try {
                //for (int i = 1; i <= 10; i++) {// para repetir la llamada a mas procesos
                    //llamada a proceso
                   // System.out.println("Iniciado proceso numero:" + i);

                    String comando = "java -jar " + ruta;//sentencia para iniciar el proceso lenguaje pasando por argumento el nombre del fichero a crear
                    System.out.println("Sentencia iniciada." + comando);
                    Runtime.getRuntime().exec(comando);//ejecutamos el comando

               // }
            } catch (IOException ex) {
                System.out.println("Fallo al llamar al Cliente");
            }
        } else {
            System.out.println("falta el nombre del archivo");
        }
    }
}
