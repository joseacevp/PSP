/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebaexamenmayo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author josea
 */
public class Servidor {
    //método que encripta el fichero que se pasa como parámetro
    //devuelve el valor de la clave privada utilizada en encriptación
    //El fichero encriptado lo deja en el archivo de nombre fichero.cifrado
    //en el mismo directorio

    private static SecretKey cifrarFichero(String file, String usuario, String password) {
        SecretKey passwordKey = null;
        try {
            FileInputStream fe = null; //fichero de entrada
            FileOutputStream fs = null; //fichero de salida
            int bytesLeidos;

//1. Crear e inicializar clave
            System.out.println("1.-Genera clave AES");
//semilla utilizada para generar la clave
            String semilla = usuario + password;

//crea un objeto para generar la clave usando algoritmo AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
// genera una clave aleatoria a partir de la semilla
            SecureRandom aleatorio = new SecureRandom(semilla.getBytes());
//inicaliza el tamaño de la clave 
            keyGen.init(128, aleatorio);
//genera la clave privada
            passwordKey = keyGen.generateKey();
// muestra la clave cifrada
            System.out.println("Clave");
            mostrarBytes(passwordKey.getEncoded());
            System.out.println();

//Se Crea el objeto Cipher para cifrar, utilizando el algoritmo AES
            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
//Se inicializa el cifrador en modo CIFRADO o ENCRIPTACIÓN
            cifrador.init(Cipher.ENCRYPT_MODE, passwordKey);
            System.out.println("2.- Cifrar con AES el fichero: " + file
                    + ", y dejar resultado en " + file + ".cifrado");
//declaración  de objetos
            byte[] buffer = new byte[1000]; //array de bytes
            byte[] bufferCifrado;
            fe = new FileInputStream(file + ".txt"); //objeto fichero de entrada
            fs = new FileOutputStream(file + ".cifrado"); //fichero de salida
//lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(buffer, 0, 1000);
            while (bytesLeidos != -1) {//mientras no se llegue al final del fichero
                //pasa texto claro al cifrador y lo cifra, asignándolo a bufferCifrado
                bufferCifrado = cifrador.update(buffer, 0, bytesLeidos);
                fs.write(bufferCifrado); //Graba el texto cifrado en fichero
                bytesLeidos = fe.read(buffer, 0, 1000);
            }
            bufferCifrado = cifrador.doFinal(); //Completa el cifrado
            fs.write(bufferCifrado); //Graba el final del texto cifrado, si lo hay
//Cierra ficheros
            fe.close();
            fs.close();

        } catch (Exception ex) {
            System.out.println("fallo en entradas salidas de datos " + ex);
        }
        return passwordKey;
    }

    public static void main(String[] args) {
        //declara e incializa objeto tipo clave secreta que recibe el valor de 
        // la función cifrarFichero
        SecretKey clave = null;

        //escrive en el fichero una cadena de texto aleatoria.
        escribirEnFichero("fichero.txt");

        //pide los datos del usuario para incorporarlos a la clave privada
        Scanner sc = new Scanner(System.in);
        System.out.println("Indicar nombre de Usuario:");
        String usuario = sc.nextLine();

        System.out.println("Indicar Password: ");
        String password = sc.nextLine();
        try {
            //Llama al método que encripta el fichero que se pasa como parámetro
            //asi como los datos del usuario
            clave = cifrarFichero("fichero", usuario, password);
        } catch (Exception e) {
        }
    }

    //escribe en el fichero una frase con caracteres aleatorios.
    public static void escribirEnFichero(String fichero) {

        int numLineas = 1;//variable que optiene por teclado el numero de lineas a escribir.
        String abecedario = "abcdefghijklmnopqrstuvwxyz";//variable con el contenido de las letras.

        try {
            //creamos el fichero.
            File archivo = new File(fichero);

            //si el archivo indicado no existe
            if (!archivo.exists()) {
                try {
                    archivo.createNewFile();
                } catch (IOException ex) {
                    System.out.println("fallo en entradas salidas de datos " + ex);
                }
            }

            RandomAccessFile raf = new RandomAccessFile(archivo, "rwd");//utilidad para controlar el acceso al fichero con permisos de escritura lectura y modificacion
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
        } catch (FileNotFoundException ex) {
            System.out.println("fallo al abrir fichero" + ex);
        } catch (IOException ex) {
            System.out.println("fallo en entradas salidas de datos " + ex);
        }

    }
    //genera numeros aleatorios recibe como parametros el numero minimo y maximo de caracteres.

    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) (Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    //método que muestra bytes
    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
}
