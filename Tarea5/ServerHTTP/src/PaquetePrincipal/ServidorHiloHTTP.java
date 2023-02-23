/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class ServidorHiloHTTP extends Thread{
    private Socket sc;

    public ServidorHiloHTTP(Socket sc) {
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
             procesaPeticion(sc);//espera a recibir mensaje del cliente
            System.out.println("Comando recibido:"+datoRecibido);
            
            //out.writeUTF(calcularDatoCalendario(datoRecibido));//manda mensaje al cliente
            //out.writeUTF(datoRecibido);//manda mensaje al cliente
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHiloHTTP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHiloHTTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    /**
   *****************************************************************************
   * procesa la petición recibida
   *
   * @throws IOException
   */
  private static void procesaPeticion(Socket socketCliente) throws IOException {
    //variables locales
    String peticion;
    String html;

    //Flujo de entrada
    InputStreamReader inSR = new InputStreamReader(
            socketCliente.getInputStream());
    //espacio en memoria para la entrada de peticiones
    BufferedReader bufLeer = new BufferedReader(inSR);

    //objeto de java.io que entre otras características, permite escribir 
    //'línea a línea' en un flujo de salida
    PrintWriter printWriter = new PrintWriter(
            socketCliente.getOutputStream(), true);

    //mensaje petición cliente
    peticion = bufLeer.readLine();

    //para compactar la petición y facilitar así su análisis, suprimimos todos 
    //los espacios en blanco que contenga
    peticion = peticion.replaceAll(" ", "");

    //si realmente se trata de una petición 'GET' (que es la única que vamos a
    //implementar en nuestro Servidor)
    if (peticion.startsWith("GET")) {
      //extrae la subcadena entre 'GET' y 'HTTP/1.1'
      peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

      //si corresponde a la página de inicio
      if (peticion.length() == 0 || peticion.equals("/")) {
        //sirve la página
        html = Paginas.html_index;
        printWriter.println(Mensajes.lineaInicial_OK);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      } //si corresponde a la página del Quijote
      else if (peticion.equals("/quijote")) {
        //sirve la página
        html = Paginas.html_quijote;
        printWriter.println(Mensajes.lineaInicial_OK);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      } //en cualquier otro caso
      else {
        //sirve la página
        html = Paginas.html_noEncontrado;
        printWriter.println(Mensajes.lineaInicial_NotFound);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      }
    
    }
  }
}
