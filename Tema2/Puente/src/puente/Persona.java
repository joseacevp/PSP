package puente;

import java.util.concurrent.Semaphore;

/**@author josea*/

public class Persona extends Thread{
    Semaphore semaforo;
    int numero;

    public Persona(Semaphore semaforo, int numero) {  
        
        this.semaforo = semaforo;
        this.numero = numero;
    }
   
    @Override
    public void run(){
        try {
            System.out.println("Persona "+numero+" intenta entrar pero está ocupado. Se queda esperando");
            semaforo.acquire();
            System.out.println("Persona "+numero+" entra en el puente");
            Thread.sleep((int) (Math.random()*1+3));
            System.out.println("Persona "+numero+" sale del puente");
            semaforo.release();
            
        } catch (Exception e) {
            System.out.println("fallo al parar el tiempo en el hilo");
        }
    }
    
}
