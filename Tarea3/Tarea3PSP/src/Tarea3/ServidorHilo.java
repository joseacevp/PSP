package Tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class ServidorHilo extends Thread {

    private Socket sc;

    public ServidorHilo(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            String dato = in.readUTF();
            //out.writeUTF("hola");
            //controlar que el comando recibido sea valido
            
            System.out.println("Conexión Realizada");
            System.out.println("Comando Recibido:"+dato);
            if (dato=="DAY"){
            out.writeUTF("dato");
            }else
            sc.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
