package puente;

import java.util.concurrent.Semaphore;

/**
 * @author josea
 */
public class Puente {

    public static void main(String[] args) {

        Semaphore semaforo = new Semaphore(1);
        int i = 0;
        boolean ciclo = true;

        while (ciclo) {//bucle para crear y lanzar los hilos
            try {
                i++;//incrementa el numero de hilo
                Persona persona = new Persona(semaforo, i);//crea y
                persona.start();// lanza un hilo

                Thread.sleep((int) (Math.random() * 1 + 3));//relentiza 2 segundos el bucle

                if (i == 10) {//cuando lance 10 hilos para el bucle
                    ciclo = false;//para el bucle
                }
            } catch (InterruptedException ex) {
                System.out.println("fallo al bloquear le tiempo");
            }

        }
    }

}
