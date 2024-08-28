package solution.racecondition;

import solution.BinSemaphore;

public class HiloContador implements Runnable {

    private final Contador contador;
    private final BinSemaphore semaphore;

    public HiloContador(Contador contador, BinSemaphore semaphore) {
        this.contador = contador;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contador.incrementarContador();
            System.out.println("Contador incrementado por : " + contador.getContador());
            semaphore.release();
        }
    }

}
