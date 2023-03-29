/*
 * Para poder aplicar las políticas de seguridad, en una aplicación local, debemos 
activar el Security Manager utilizando la opción –Djava.security.manager, por lo
que ejecutaremos:

java –Djava.security.manager GetProps 


ruta de policytool para cambiar las politicas de seguridad de la aplicacion
C:\Program Files\Java\jdk-9.0.4\bin


 */
package propiedadessistema;
import java.lang.*;
import java.security.*;
/**
 *
 * @author josea
 */
public class PropiedadesSistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String s;
 
        try {
            System.out.println("About to get os.name property value");
 
            s = System.getProperty("os.name", "not specified");
            System.out.println("  The name of your operating system is: " + s);
 
            System.out.println("About to get java.version property value");
 
            s = System.getProperty("java.version", "not specified");
            System.out.println("  The version of the JVM you are running is: " + s);
 
            System.out.println("About to get user.home property value");
 
            s = System.getProperty("user.home", "not specified");
            System.out.println("  Your user home directory is: " + s);
 
            System.out.println("About to get java.home property value");
 
            s = System.getProperty("java.home", "not specified");
            System.out.println("  Your JRE installation directory is: " + s);
 
         } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        } 
    
    }
    
}
