package Tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Socket sc = new Socket("localhost",5000);
            
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            Scanner sn = new Scanner(System.in);
            sn.useDelimiter("\n");//Captura lo escrito antes de salto de linea
            
            System.out.println("-> ");
            
            String day,month,year;
            
            day = sn.next();
            
            out.writeUTF(day);
            //mensaje si el dato es incorrecto?
            
            sc.close();
            
        } catch (Exception e) {
            System.out.println("fallo al conectar con el servidor");
        }
  
    }
    
}
