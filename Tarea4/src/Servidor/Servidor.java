 
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 * servidor siempre espera peticiones y 
 * cuando un cliente llega lo asigna un 
 * hilo con la instancia de servidorHilo
 */
public class Servidor {
 
    public static void main(String[] args) {
        try {
            //creamos instancia de ServerSokect
            ServerSocket servidor = new ServerSocket(50000);
            System.out.println("Servidor iniciado");
            
            while(true){
                Socket sc = servidor.accept();
                //iniciamos el servidor hilo
                ServidorHilo s = new ServidorHilo(sc);//instancia del servidorhilo parametro Socket
                s.start();//inicio de hilo
            }
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
