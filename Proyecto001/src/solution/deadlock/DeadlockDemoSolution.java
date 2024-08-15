package solution.deadlock;

import solution.BinSemaphore;

public class DeadlockDemoSolution {


    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();

        BinSemaphore semaphore = new BinSemaphore();

        Thread t1 = new Thread(new solution.deadlock.SyncThread(o1, o2, semaphore),"hilo1");
        Thread t2 = new Thread(new solution.deadlock.SyncThread(o2, o3, semaphore),"hilo2");
        Thread t3 = new Thread(new solution.deadlock.SyncThread(o3, o1, semaphore),"hilo3");

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
    public BinSemaphore semaphore;

    public SyncThread(Object ob1, Object ob2, BinSemaphore semaphore) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        System.out.println(name + " generando lock en " + ob1);
        try {
            semaphore.acquire();

            synchronized (ob1) {
                System.out.println(name + " lock en " + ob1);
                work();
                System.out.println(name + " generando lock en " + ob2);
                    synchronized (ob2) {
                        System.out.println(name + " lock en " + ob2);
                        work();
                    }
                }
                System.out.println(name + " lock liberado en " + ob2);
            System.out.println(name + " lock liberado en " + ob1);

            System.out.println("Final");

        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        e.printStackTrace();
    } finally {

            semaphore.release();
        }
    }

    private void  work(){
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
