/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrar_claveprivadaaes;

/**
 *
 * @author IMCG
 */
import java.security.*; //JCA
import javax.crypto.*; //JCE
import java.io.*; //ficheros
import java.security.spec.KeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

//Programa que encripta y desencripta un fichero
//mediante clave privada o simétrica utilizando el algoritmo DES
public class Main {

    public static void main(String[] Args) {
        //declara e incializa objeto tipo clave secreta
        SecretKey clave = null;
        //escrive en el fichero una cadena de texto aleatoria.
        escribirEnFichero("fichero.txt");

        //pide los datos del usuario para incorporarlos a la clave privada
        Scanner sc = new Scanner(System.in);
        System.out.println("Indicar nombre de Usuario:");
        String usuario = sc.nextLine();
        
        System.out.println("Indicar Password: ");
        String password = sc.nextLine();
        //llama a los métodos que encripta/desencripta un fichero
        try {
            //Llama al método que encripta el fichero que se pasa como parámetro
            //asi como los datos del usuario
            clave = cifrarFichero("fichero",usuario, password);
            //Llama la método que desencripta el fichero pasado como primer parámetro
            descifrarFichero("fichero.cifrado", clave,
                    "fichero.descifrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método que encripta el fichero que se pasa como parámetro
    //devuelve el valor de la clave privada utilizada en encriptación
    //El fichero encriptado lo deja en el archivo de nombre fichero.cifrado
    //en el mismo directorio
    private static SecretKey cifrarFichero(String file, String usuario, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey passwordKey = null;
        try {
            FileInputStream fe = null; //fichero de entrada
            FileOutputStream fs = null; //fichero de salida
            int bytesLeidos;

            //1. Crear e inicializar clave
            System.out.println("1.-Genera clave AES");

            passwordKey = generateKey(password, usuario);

//            //crea un objeto para generar la clave usando algoritmo AES
//            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//            keyGen.init(128); //se indica el tamaño de la clave
//            SecretKey clave = keyGen.generateKey(); //genera la clave privada
            System.out.println("Clave");
            mostrarBytes(passwordKey.getEncoded()); //muestra la clave
            System.out.println();

            //Se Crea el objeto Cipher para cifrar, utilizando el algoritmo DES
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passwordKey;
    }
    //método que desencripta el fichero pasado como primer parámetro file1
    //pasándole también la clave privada que necesita para desencriptar, key
    //y deja el fichero desencriptado en el tercer parámetro file2

    private static void descifrarFichero(String file1, SecretKey key, String file2) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
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
    }

    //método que muestra bytes
    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }

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
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) (Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    private static SecretKey generateKey(String password, String salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

}
