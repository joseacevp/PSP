/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lenguaje;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aplicación que crea un fichero que contiene un numero de palabras indicado
 * por argumentos que se forman con la concatenación de letras al azar
 *
 * @author josea
 */
public class Lenguaje {

    /**
     * @param args la plicación requiere de dos argumentos que se han de indicar
     * en la ejecución de esta el primero es el numero de palabras y es segundo
     * el fichero donde se alamcenaran.
     */
    
    public static void main(String[] args) {

        String abecedario = "abcdefghijklmnopqrstuvwxyz";//variable con el contenido de las letras.
        String fichero;// nombre que tendra el fichero
        FileLock bloqueador = null;//utilidad que bloquea el uso del fichero mientras se esta escribiendo datos.

        if (args.length == 2) {//si falta alguno de los dos argumentos 
            try {
                int numLineas = Integer.parseInt(args[0]);//variable que optiene por teclado el numero de lineas a escribir.
               
                //control de escritura de la ruta para el fichero segun el SO usado
                String osNombre = System.getProperty("os.name");//optiene el nombre del sistema operativo.
                //si esta en windows
                if (osNombre.toUpperCase().contains("WIN")) {//si en el contenido del nombre del so esta la palabra win es windows
                    fichero = args[1].replace("\\", "\\\\");//control del tipo de barra para escribir la ruta del fichero.
                } else {
                    fichero = args[1];//si no es windows toma el argumeto pasado por teclado
                }
                //creamos el fichero.
                File archivo = new File(fichero);

                //si el archivo indicado no existe
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }

                RandomAccessFile raf = new RandomAccessFile(archivo, "rwd");//utilidad para controlar el acceso al fichero con permisos de escritura lectura y modificacion
                bloqueador = raf.getChannel().lock();//pasamos la utilidad de bloquear a la variable FileLock
                //antes de entrar en el proceso de escritura de la linea en el fichero hay que indicar que la linea se escriba al final del fichero.
                raf.seek(archivo.length());//indica que la escritura se realice al final del fichero.

                //proceso de escritura simultanea 
                for (int i = 0; i < numLineas; i++) {
                    String linea = " ";
                    int numCaracter = generaNumeroAleatorio(1, 10);//genera el numero de letras que escribe de forma aleatoria.

                    for (int j = 0; j < numCaracter; j++) {
                        //añade a la linea los caracteres numero de estos qenerados aleatoriamenta minimo 0 maximo abecedario -1
                        linea += abecedario.charAt(generaNumeroAleatorio(0, abecedario.length() - 1));
                    }
                    raf.writeChars(linea + "\n");//escribe en el fichero la linea                         
                }

                //bloque de procesos de escritura en archivo.
                bloqueador.release();//para el bloqueador dando acceso al archivo a otro proceso
                bloqueador = null;
                raf.close();//cierra la utilidad de acceso al archivo.
               
            } catch (IOException ex) {
                Logger.getLogger(Lenguaje.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("Faltan argumentos para que se pueda ejecutar el programa");
        }

    }

    /**
     * //genera un numeros aleatorios indicando cuantos de maximo y cuantos
     * minimo.
     *
     * @param minimo minimo numero de numeros
     * @param maximo maximo numero de numeros
     *
     */
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) (Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

}
