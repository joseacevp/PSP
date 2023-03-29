package cifrardescifrar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author josea
 */
public class CifrarDescifrar {

    public static void main(String[] args) {

        //declara e inicializa objeto tipo clave secreta
        SecretKey clave = null;

        //llama a los mátedos que encripta/desencripta un fichero
        try {
            //llama al método que encripta el fichero que se pasa como parámetro
            clave = cifrarFichero("fichero");
            //llama al método que desencripta el fichero pasado como primer parámetro
            descifrarFichero("fichero.cifrado", clave, "fichero.descifrado");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //método cifrarFichero

    private static SecretKey cifrarFichero(String file) {

        // String file = File;
        try {
            FileInputStream fe = null;//fichero de entrada
            FileOutputStream fs = null;//fichero de salida
            int bytesLeidos;

            //1. Crear el iniciador de la clave
            System.out.println("1. -Genera clave DES");
            //crea un objeto para generar la clave usando algoritmo DES
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);//se indica el tamaño de  la clave
            SecretKey clave = keyGen.generateKey();//genera la clave privada

            System.out.println("Clave");
//            mostrarBytes(clave.getEncoded());//muestra la clave
            System.out.println();

            //se Crea el objeto Cipher para cifrar, utilizando el algoritmo DES
            Cipher cifrador = Cipher.getInstance("DES");
            //se inicializa el cifrador en modo CIFRADO
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            System.out.println("2. - Cifrar con DES el fichero: " + file + ",y dejar resultado en " + file + ".cifrado");

            //declaración de objetos
            byte[] buffer = new byte[1000];//array de bytes
            byte[] bufferCifrado;
            fe = new FileInputStream(file + ".txt");//objeto fichero de entrada
            fs = new FileOutputStream(file + ".cifrado");//fichero de salida

            //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(buffer, 0, 1000);

            while (bytesLeidos != -1) {//mientras no se llegue al final del fichero
                //pasa texto claro al cifrador y lo cifra, asignándo a bufferCifrado
                bufferCifrado = cifrador.update(buffer, 0, bytesLeidos);
                fs.write(bufferCifrado);//Graba el texto cifrado en fichero

                bytesLeidos = fe.read(buffer, 0, 1000);

            }
            bufferCifrado = cifrador.doFinal();
            fs.write(bufferCifrado);//Graba el final del texto cifrado, si lo hay
            fe.close(); //Cierra fichero
            fs.close();
            return clave;

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CifrarDescifrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    private static void mostrarBytes(byte[] encoded) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private static void descifrarFichero(String file1, SecretKey key, String file2) {
        try {
            FileInputStream fe = null;//fichero de entrada
            FileOutputStream fs = null;//fichero de salida
            int bytesLeidos;

            Cipher cifrador = Cipher.getInstance("DES");
            //3.- Poner cifrador en modo DESCIFRADO o DESENCRIPTADO
            cifrador.init(Cipher.DECRYPT_MODE, key);
            System.out.println("3.- Descifrar con DES el fichero: " + file1 + ", y dejar en " + file2);
            fe = new FileInputStream(file1);
            fs = new FileOutputStream(file2);
            byte[] bufferClaro;
            byte[] buffer = new byte[1000];//array de bytes
            //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(buffer, 0, 1000);
            while (bytesLeidos != -1) {//mientras no se llegue al final del fichero
                //pasa texto cifrado al cifrador y lo descifra, asignándolo a bufferClaro
                bufferClaro = cifrador.update(buffer, 0, bytesLeidos);
                fs.write(bufferClaro);//graba el texto claro en fichero
                bytesLeidos = fe.read(buffer, 0, 1000);

            }
            bufferClaro = cifrador.doFinal();//Completa el descifrado
            fs.write(bufferClaro);//graba el final del texto claro, si lo hay
            //cierra archivos
            fe.close();
            fs.close();

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("");
        } catch (NoSuchPaddingException ex) {
            System.out.println("");
        } catch (InvalidKeyException ex) {
            System.out.println("");
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException ex) {
            System.out.println("");
        } catch (IllegalBlockSizeException ex) {
            System.out.println("");
        } catch (BadPaddingException ex) {
            System.out.println("");
        }
    }

}
