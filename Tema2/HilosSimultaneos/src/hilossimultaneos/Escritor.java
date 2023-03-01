/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilossimultaneos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author josea
 */
public class Escritor {

    private String nombreFichero = "datos.txt";

    public void escribir() {
        try {
            String osNombre = System.getProperty("os.name");//optiene el nombre del sistema operativo.
            //si esta en windows
            if (osNombre.toUpperCase().contains("WIN")) {//si en el contenido del nombre del so esta la palabra win es windows
                nombreFichero.replace("\\", "\\\\");//control del tipo de barra para escribir la ruta del fichero.
            }
            //creamos el fichero.
            File archivo = new File(nombreFichero);

            //si el archivo indicado no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(archivo, "rwd");//utilidad para controlar el acceso al fichero con permisos de escritura lectura y modificacion
            raf.seek(archivo.length());//indica que la escritura se realice al final del fichero.

            /*proceso de escritura simultanea 
            bucle for para formar un numero de 5 digitos y 
            escribir lo en el fichero detras del anterior si existia
             */
            for (int i = 0; i < (int) (Math.random() * 5+1); i++) {//escribe un numero de longitud entre 1 y 5 digitos
                int num = (int) (Math.random() * 9);
                String linea = String.valueOf(num);
                raf.writeChars(linea);//escribe en el fichero el numero
            }
            raf.writeChars("\n");//escribe en el fichero un salto de linea.

            //System.out.println("fichero escrito con exito");
        } catch (IOException ex) {
            System.out.println("fallo al acceder al fichero");
        }
    }

}
