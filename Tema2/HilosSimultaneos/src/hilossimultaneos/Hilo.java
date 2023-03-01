/*
  */
package hilossimultaneos;

import java.util.concurrent.Semaphore;

/** @author josea */
public class Hilo extends Thread {

    Semaphore semaforo;
    int numero;

    public Hilo(Semaphore semaforo, int numero) {//constructor recive el semaforo comun y el numero de hilo
        this.semaforo = semaforo;
        this.numero = numero;
    }
   
    @Override
    public void run() {//metodo run par lanzar los hilos
        try {
            System.out.println("Hilo" + this.numero + "  se queda esperando para escribir en el archivo");//indica que esta a la espera
            semaforo.acquire();//acquiere el proceso
            System.out.println("Hilo" + this.numero + "  bloquea el semáforo para escribir en el archivo");//indica que esta en el proceso
            Escritor escritor = new Escritor();//instancia de la clase para escribir en archivo .txt
            escritor.escribir();//escribe en el archivo .txt
            Thread.sleep(5000);//espera 5 segundos para salir del proceso.
            System.out.println("Hilo" + this.numero + " libera el semáforo");//indica que termino el proceso 
            semaforo.release();//termina el proceso y libera el semaforo
        } catch (InterruptedException ex) {
            System.out.println("fallo al para el tiempo en el hilo");
        }
    }
}
