/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrologger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.*;

/**
 *Establezco el nivel de seguridad de las actividades que quiero registrar.
Niveles de seguridad de los registros
Nivel	Importancia
SEVERE	7 (Máxima)
WARNING	6
INFO	5
CONFIG	4
FINE	3
FINER	2
FINEST	1 (Mínima)
Por ejemplo, si deseo registrar sólo los registros más graves ejecutamos

logger.setLevel(Level.SEVERE);

Además, de los niveles anteriores, si queremos registrar todos los eventos utilizaremos:

logger.setLevel(Level.ALL);

Y si no queremos reegistrar ningún evento, ejecutaremos:

logger.setLevel(Level.OFF);

Una vez inicializado correctamente el logger, el siguiente paso es ir registrando en la aplicación los diferentes registros. Por ejemplo, para añadir un registro de nivel de importancia medio (WARNING) lo creamos de la siguiente forma:

logger.log(Level.WARNING,"Mi primer log");
 */
public class CrearFicheroLogger {

    public void crearLogger() {
        Logger logger = Logger.getLogger("MyLog");

        try {
            // Configuro el logger y establezco el formato
            FileHandler fh = null;
            try {
                fh = new FileHandler("MyLogFile.log", true);
            } catch (IOException ex) {
                Logger.getLogger(CrearFicheroLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // Añado un mensaje al log   
            logger.log(Level.WARNING, "Mi primer log");
            // registrar todos los eventos utilizaremos
            logger.setLevel(Level.ALL);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
