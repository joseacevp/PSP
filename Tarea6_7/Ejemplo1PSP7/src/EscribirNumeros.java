// Nota: Este código no funciona. Es simplemente para uso educativo
import java.io.*;
import java.util.Vector;
import java.util.Random;

class EscribirNumeros {
    private Vector<Integer> numeros;
    private static final int SIZE = 100;

    public EscribirNumeros () throws IOException{
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
    }

     public static void main( String[] arg ) throws IOException {
          new EscribirNumeros ();
     }
}
