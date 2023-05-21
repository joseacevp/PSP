package pruebaexamenmayo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author josea
 */
public class Cliente {
    //método que desencripta el fichero pasado como primer parámetro file1
//pasándole también la clave privada que necesita para desencriptar, key
//y deja el fichero desencriptado en el tercer parámetro file2
    private  void descifrarFichero(String file1, String clave, String file2) {
        try {
            //combierte un string en una clave privada
            byte[] decoKey= Base64.getDecoder().decode(clave);
            SecretKey key = new SecretKeySpec (decoKey,0,decoKey.length,"AES");
            ////////////////////////////////////////////////
            FileInputStream fe = null; //fichero de entrada
            FileOutputStream fs = null; //fichero de salida
            int bytesLeidos;
            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");

            //3.- Poner cifrador en modo DESCIFRADO o DESENCRIPTACIÓN
            cifrador.init(Cipher.DECRYPT_MODE, key);
            System.out.println("3.- Descifrar con AES el fichero: " + file1
                    + ", y dejar en  " + file2);
            fe = new FileInputStream(file1);
            fs = new FileOutputStream(file2);
            byte[] bufferClaro;
            byte[] buffer = new byte[1000]; //array de bytes
            //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(buffer, 0, 1000);
            while (bytesLeidos != -1) {//mientras no se llegue al final del fichero
                //pasa texto cifrado al cifrador y lo descifra, asignándolo a bufferClaro
                bufferClaro = cifrador.update(buffer, 0, bytesLeidos);
                fs.write(bufferClaro); //Graba el texto claro en fichero
                bytesLeidos = fe.read(buffer, 0, 1000);
            }
            bufferClaro = cifrador.doFinal(); //Completa el descifrado
            fs.write(bufferClaro); //Graba el final del texto claro, si lo hay

            //cierra archivos
            fe.close();
            fs.close();
        } catch (Exception ex) {
            System.out.println("fallo en entradas salidas de datos " + ex);
        }
    }

//método que muestra bytes
    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
//escribe en el fichero una frase con caracteres aleatorios.
    
    
    public static void main(String[] args) {
       //pide los datos de la clave para descifrar el fichero
        Scanner sc = new Scanner(System.in);
        System.out.println("Indicar clave:");
        String clave = sc.nextLine();
        //llama a la funcion para descifrar el fichero
        Cliente cliente = new Cliente();
        cliente.descifrarFichero("fichero.cifrado", clave, "ficheroDescifrado.txt");
    }
}
