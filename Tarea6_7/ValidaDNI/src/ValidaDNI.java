
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * A modo de ejemplo, a continuación se muestran varios ejemplos de expresiones:

Teléfono (formato 000-000000)
pat=Pattern.compile("[0-9]{3}-[0-9]{6}");

DNI
pat=Pattern.compile("[0-9]{8}-[a-zA-Z]");

Provincias andaluzas
pat=Pattern.compile("Almería","Granada","Jaén","Málaga","Sevilla","Cádiz","Córdoba","Huelva");

Le pasamos al evaluador de expresiones el texto a comprobar.
mat=pat.matcher(texto_a_comprobar);
Comprobamos si hay alguna coincidencia:
if(mat.find()){
	// Coincide con el patrón 
	}else{
	// NO coincide con el patrón 
	}
 */
public class ValidaDNI {

    String dni_cliente = new String();
    Pattern pat = null;
    Matcher mat = null;
    // para leer del teclado
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void validar(){
        
       try {
            System.out.println("Introduce tu DNI (Formato 00000000-A):");
            dni_cliente = reader.readLine();
            
            pat = Pattern.compile("[0-9]{8}-[a-zA-Z]");
            mat = pat.matcher(dni_cliente);
            
            if (mat.find()) {
                System.out.println("Correcto!!  " + dni_cliente);
            } else {
                System.out.println("El DNI esta mal  " + dni_cliente);
            }
        } catch (IOException ex) {
            System.out.println("Fallo al introducir valores");
        }
           
    }
            
}
