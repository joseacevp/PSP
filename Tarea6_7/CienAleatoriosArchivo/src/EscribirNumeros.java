// Nota: Este código no funciona. Es simplemente para uso educativo
import java.io.*;
import java.util.Vector;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class EscribirNumeros {
    private Vector<Integer> numeros;
    private static final int SIZE = 100;

    public EscribirNumeros () {
        try {
            // Generamos el vector con números aleatorios
            numeros = new Vector<Integer>(SIZE);
            Random randomGenerator = new Random();
            
            for (int i = 0; i < SIZE; i++){
                Integer num=randomGenerator.nextInt(100);
                numeros.addElement(new Integer(num));
            }
            
            // Guardamos el vector en un fichero
            PrintWriter out = null;
            System.out.println("Entrando a la zona Try");
            out = new PrintWriter(new FileWriter("Salida.txt"));
            
            for (int i = 0; i < SIZE; i++)
                out.println("Valor de: " + i + " = " + numeros.elementAt(i));
            
            out.close();
        } catch (IOException ex) {
            System.out.println("Fallo al abrir fichero");
            Logger.getLogger(EscribirNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public static void main( String[] arg )  {
          new EscribirNumeros ();
     }
}
