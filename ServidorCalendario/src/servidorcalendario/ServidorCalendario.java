/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcalendario;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class ServidorCalendario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //creamos instancia de ServerSokect
            ServerSocket servidor = new ServerSocket(50000);
            System.out.println("Servidor iniciado");
             
            while(true){
             Socket sc = servidor.accept();
                //iniciamos el servidor hilo
                ServidorHiloCalendario s = new ServidorHiloCalendario(sc);//instancia del servidorhilo parametro Socket
                s.start();//inicio de hilo
               
            }
           
            
            
        } catch (IOException ex) {
            System.out.println("Fallo en inicio del servidor");
        }
    }
    
}
