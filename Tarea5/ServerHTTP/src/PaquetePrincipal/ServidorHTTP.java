package PaquetePrincipal;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * *****************************************************************************
 * Servidor HTTP que atiende peticiones de tipo 'GET' recibidas por el puerto
 * 8066
 *
 * NOTA: para probar este código, comprueba primero de que no tienes ningún otro
 * servicio por el puerto 8066 (por ejemplo, con el comando 'netstat ' si estás
 * utilizando Windows) 'netstat -na' 'netstat -na | findstr 8066' En el ejemplo
 * del apartado 5.1 en el que se basa la tarea, a la hora de probarlo no
 * funciona en algunos navegadores más modernos por temas de seguridad. Para
 * probarla podéis hacerlo desde la consola utilizando la utilidad curl que
 * suele estar en todos los SSOO. En windows por ejemplo sería: curl
 * localhost:8066 Y si queréis ver las cabeceras http curl -v localhost:8066
 * Esta utilidad sirve para conectarse a servidores web y ver la respuesta en
 * modo texto, tanto del html/js/css como de las cabeceras, es muy útil para
 * programación web de apis, servicios rest, etc. Podéis probar por ejemplo curl
 * -v google.es y ver la diferencia con respecto a curl -v www.google.es
 *
 * @author IMCG
 */
class ServidorHTTP {

    /**
     * **************************************************************************
     * procedimiento principal que asigna a cada petición entrante un socket
     * cliente, por donde se enviará la respuesta una vez procesada
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        //Asociamos al servidor el puerto 8066
        ServerSocket socServidor = new ServerSocket(8066);
        imprimeDisponible();//salida por consola de mensaje bienvenida
        Socket socCliente;

        try {
            //ante una petición entrante, procesa la petición por el socket cliente
            //por donde la recibe
            while (true) {
                //a la espera de peticiones
                socCliente = socServidor.accept();
                ServidorHiloHTTP servidorHiloHTTP = new ServidorHiloHTTP(socCliente);
                servidorHiloHTTP.start();

                //atiendo un cliente
                System.out.println("Atendiendo al cliente ");
                //procesaPeticion(socCliente);
                //cierra la conexión entrante
                // socCliente.close();
                System.out.println("cliente atendido");
            }
        } catch (Throwable e) {
            System.out.println("Fallo al crear servidor");
        }

    }

    /**
     * **************************************************************************
     * muestra un mensaje en la Salida que confirma el arranque, y da algunas
     * indicaciones posteriores
     */
    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
                + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
                + "localhost:8066/q\n para simular un error");
    }
}
