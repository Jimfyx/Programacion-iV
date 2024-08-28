package solution.racecondition;


import solution.BinSemaphore;

public class RaceConditionSolution {

    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador();

        BinSemaphore semaphore = new BinSemaphore();

        Runnable tarea1 = new HiloContador(contador, semaphore);
        Runnable tarea2 = new HiloContador(contador, semaphore);

        Thread t1 = new Thread(tarea1);
        Thread t2 = new Thread(tarea2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final contador: " + contador.getContador());
    }
}
