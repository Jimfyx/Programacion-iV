package issues.semaforo.solutions.deadlock;


import issues.semaforo.solutions.SemaforoBinario;

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        SemaforoBinario semaforoBinario = new SemaforoBinario();

        Thread t1 = new Thread(new SyncThread(o1, o2, semaforoBinario),"hilo1");
        Thread t2 = new Thread(new SyncThread(o2, o3, semaforoBinario),"hilo2");
        Thread t3 = new Thread(new SyncThread(o3, o1, semaforoBinario),"hilo3");

        t1.start();
        //Thread.sleep(5000);
        t2.start();
        //Thread.sleep(5000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println("Finalizado");
    }

}

class SyncThread implements Runnable {

    public Object ob1;
    public Object ob2;
    private SemaforoBinario semaforoBinario;
    public SyncThread(Object ob1, Object ob2, SemaforoBinario semaforoBinario) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaforoBinario = semaforoBinario;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " generando lock en " + ob1);
        try {
            semaforoBinario.acquire();

            synchronized (ob1) {
                System.out.println(name + " lock en " + ob1);
                work();
                System.out.println(name + " generando lock en " + ob2);
                synchronized (ob2) {
                    System.out.println(name + " lock en " + ob2);
                    work();
                }
                System.out.println(name + " lock liberado en " + ob2);
            }
            semaforoBinario.release();
        }catch (InterruptedException e) {
            System.out.println(name + " interrumpido");
        }
        System.out.println(name + " lock liberado en " + ob1);
        System.out.println("Final");
    }

    private void  work(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
