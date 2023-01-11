package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            //creamos instancia de Socket para conectarnos al servidor
            Socket sc = new Socket("localhost", 50000);
            //metodo para leer en el servidor
            DataInputStream in = new DataInputStream(sc.getInputStream());
            //metodo para escribir del servidor
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //metodo para escribir desde teclado
            Scanner sn = new Scanner(System.in);
            
            System.out.println("->");
            String mensajeEnviado = sn.nextLine();//captura el dato del teclado
            out.writeUTF(mensajeEnviado);//manda el mensaje al servidor
            
            String mensajeRecibido = in.readUTF();//queda a la espera del mensaje
            System.out.println(mensajeRecibido);

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
