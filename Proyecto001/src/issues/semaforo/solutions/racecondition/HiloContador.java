package issues.semaforo.solutions.racecondition;

import issues.semaforo.solutions.SemaforoBinario;

public class HiloContador implements Runnable {

    private final Contador contador;
    private final SemaforoBinario semaforoBinario;

    public HiloContador(Contador contador, SemaforoBinario semaforoBinario) {
        this.contador = contador;
        this.semaforoBinario = semaforoBinario;
    }

    @Override
    public void run() {
        try {
            semaforoBinario.acquire();
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
            semaforoBinario.release();
        }catch (InterruptedException e) {
            System.out.println("Sucedio una excepcion interrumpida " + e.getMessage());
        }
    }

}
