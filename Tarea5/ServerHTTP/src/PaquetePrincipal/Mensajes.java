
package PaquetePrincipal;

/**
 *
 * @author IMCG
 */
//Mensajes que intercambia el Servidor con el Cliente según protocolo HTTP
public class Mensajes {
   
 public static final String lineaInicial_OK = "HTTP/1.1 200 OK"
         + "Content-Type:text/html;chatset=UTF-8 "
         + "Content-Lenght:1024";
  public static final String lineaInicial_NotFound =
          "HTTP/1.1 404 Not Found";
//  public static final String lineaInicial_BadRequest =
//          "HTTP/1.1 400 Bad Request";
 
}
