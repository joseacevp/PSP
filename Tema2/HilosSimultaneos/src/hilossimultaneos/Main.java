/*
 * Tarea 2 de Programacion de Servicios y Procesos
 */
package hilossimultaneos;

import java.util.concurrent.Semaphore;

/**
 * * @author josea
 */
public class Main {

    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);//semaforo comun de un solo hilo
        int i = 0;//numero de hilo
        boolean ciclo = true;//variable para terminar el bucle

        while (ciclo) {//bucle para crear y lanzar los hilos
            try {
                i++;//incrementa el numero de hilo
                Hilo hilo = new Hilo(semaforo, i);//crea y
                hilo.start();// lanza un hilo
               
                Thread.sleep(2000);//relentiza 2 segundos el bucle

                if (i == 10) {//cuando lance 10 hilos para el bucle
                    ciclo = false;//para el bucle
                }
            } catch (InterruptedException ex) {
                System.out.println("fallo al bloquear le tiempo");
            }

        }
    }

}
