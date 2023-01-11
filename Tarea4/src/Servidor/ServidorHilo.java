/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea servidor hilo se encanga de la cumunicaccion entre cliente y
 * servidor para que varios clientes puedan conectarse a la vez al servidor
 */
public class ServidorHilo extends Thread {
    private String dato;
    private Socket sc;

    public ServidorHilo(Socket sc) {
        this.sc = sc;
    }

    //iniciciamos el hilo con el metodo run
    @Override
    public void run() {
        //System.out.println("Conexión realizada");
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            //metodo para leer en el servidor
            in = new DataInputStream(sc.getInputStream());
            //metodo para escribir del servidor
            out = new DataOutputStream(sc.getOutputStream());
            
            
            String datoRecibido = null;
            datoRecibido = in.readUTF();//espera a recibir mensaje del cliente
            System.out.println("Comando recibido:"+datoRecibido);
            
            
            out.writeUTF(calcularDatoCalendario(datoRecibido));//manda mensaje al cliente
            //out.writeUTF(datoRecibido);//manda mensaje al cliente
            
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

    private String calcularDatoCalendario(String dato) {
        
        this.dato = dato;
        Date date = new Date();
        DateFormat dia = new SimpleDateFormat("dd");
        DateFormat mes = new SimpleDateFormat("MM");
        DateFormat ayo = new SimpleDateFormat("yyyy");
        DateFormat todo = new SimpleDateFormat("dd,MM,yyyy");
        String respuesta;
      

        if (dato == "DAY") {
            respuesta=dia.format(date);
        } else if (dato == "MONTH") {
            respuesta=mes.format(date);

        } else if (dato == "YEAR") {
            respuesta=ayo.format(date);

        } else if (dato == "ALL") {
            respuesta=todo.format(date);
        } else if (dato == "END")
        {
            respuesta="conexiones cerradas";
            
        }else 
        {
            respuesta="No se reconoce el comando solicitado";
        }

        return respuesta;
    }
}
