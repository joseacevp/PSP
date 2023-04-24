package encriptadoaes;

import java.io.UnsupportedEncodingException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author josea
 */
public class EncriptadoAES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
             String claveEncriptacion = "12345678";
            String datosOriginales = "hola1234";
//            
//            EncriptadorAES encriptador = new EncriptadorAES();
//            
//            String encriptado = encriptador.encriptar(datosOriginales, claveEncriptacion);
//            String desencriptado = encriptador.desencriptar(encriptado, claveEncriptacion);
//
            String encriptado;
            String desencriptado;
            encriptado = encriptar(datosOriginales, claveEncriptacion);
            desencriptado = desencriptar(encriptado, claveEncriptacion);

            System.out.println("Cadena Original: " + datosOriginales);
            System.out.println("Escriptado     : " + encriptado);
            System.out.println("Desencriptado  : " + desencriptado);
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            java.util.logging.Logger.getLogger(EncriptadoAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private static SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");

        return secretKey;
    }
//

    public static String encriptar(String datos, String claveSecreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = crearClave(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);

        return encriptado;
    }
    //

    public static String desencriptar(String datosEncriptados, String claveSecreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = crearClave(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datos = new String(datosDesencriptados);

        return datos;
    }
}
