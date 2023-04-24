/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptadoclaversa;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author josea
 */
public class EncriptadoClaveRSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //        Veamos como realizar este tipo de estrategia en el ejemplo RSA.java.
//        En primer lugar creamos una clave simétrica, tipo Blowfish de 128 bits,
//        para cifrar el texto.

            System.out.println("Generando clave Blowfish...");
            KeyGenerator generador = KeyGenerator.getInstance("Blowfish");
            generador.init(128);
            Key claveBlowfish = generador.generateKey();
            System.out.println("Formato: " + claveBlowfish.getFormat());

//        A continuación generamos el par de claves RSA (publica y privada).
            System.out.println("Generando par de claves RSA...");
            KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
            generadorRSA.initialize(1024);
            KeyPair claves = generadorRSA.genKeyPair();
            System.out.println("Generada la clave asimétrica.");

//        Ya podemos crear e inicializar el cifrador RSA que se va a encargar de encriptar la clave 
//        Blowfish con la parte pública del par RSA.
            Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cifradorRSA.init(Cipher.ENCRYPT_MODE, claves.getPublic());

//            Una vez tenemos este cifrador cogemos los byte de la clave Blowfish y los encriptamos
            byte[] bytesClaveBlowfish = claveBlowfish.getEncoded();
            byte[] claveBlowfishCifrada = cifradorRSA.doFinal(bytesClaveBlowfish);
//            Desencriptamos la clave Blowfish con la parte privada del par RSA.
            cifradorRSA.init(Cipher.DECRYPT_MODE, claves.getPrivate());
            byte[] bytesClaveBlowfish2 = cifradorRSA.doFinal(claveBlowfishCifrada);

//            Finalmente recreamos la clave Blowfish.
            SecretKey nuevaClaveBlowfish = new SecretKeySpec(bytesClaveBlowfish2, "Blowfish");
            System.out.println(Arrays.toString(nuevaClaveBlowfish.getEncoded()));
//            Codificación de Claves Públicas y Privadas

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptadoClaveRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EncriptadoClaveRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(EncriptadoClaveRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(EncriptadoClaveRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(EncriptadoClaveRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
