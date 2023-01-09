 package Tarea3;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author josea
 */
public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Esperando conexion con el cliente");
        
            
            while(true){
                Socket sc= servidor.accept(); 
                
                ServidorHilo hilo = new ServidorHilo(sc);
                hilo.start();
            }
        } catch (Exception e) {
            System.out.println("Fallo en conexion Servidor");
        }
        
        
    }
    
}
