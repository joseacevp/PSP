/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoproveedores;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

/**
 *
 * @author josea
 */
public class InfoProveedores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean listarProps = false;
    if ( args.length > 0 && args[0].equals("-l") )
      listarProps=true;
    System.out.println("------------------------------------");
    System.out.println("Proveedores instalados en su sistema");
    System.out.println("------------------------------------");
    Provider[] listaProv = Security.getProviders();
    for (int i = 0; i < listaProv.length; i++) {
      System.out.println("Núm. proveedor : "    + (i + 1));
      System.out.println("Nombre         : "    + listaProv[i].getName());
      System.out.println("Versión        : "    + listaProv[i].getVersion());
      System.out.println("Información    :\n  " + listaProv[i].getInfo());
      System.out.println("Propiedades    :");
      if (listarProps) {
        Enumeration propiedades = listaProv[i].propertyNames();
        while (propiedades.hasMoreElements()) {
          String clave = (String) propiedades.nextElement();
          String valor = listaProv[i].getProperty(clave);
          System.out.println("  " + clave + " = " + valor);
        }
      }
      System.out.println("------------------------------------");
    }
    }
    
}
