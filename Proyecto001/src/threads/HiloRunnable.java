package threads;

public class HiloRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("HiloRunnable started: " + Thread.currentThread().getName());
    }
}
