package solution;

public class BinSemaphore {
    private int flag = 0; // 0 unlock, 1 lock
    public synchronized void acquire() throws InterruptedException {
        while (flag != 0) {
            wait();
        }
        flag = 1; // cambia estado a lock
    }

    public synchronized void release() {
        flag = 0; // cambia estado a unlock
        notify(); // avisa que se libero el recurso
    }
}
