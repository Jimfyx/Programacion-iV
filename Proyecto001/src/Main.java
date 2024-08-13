import issues.racecondition.Contador;
import issues.racecondition.HiloContador;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador();

        Runnable tarea1 = new HiloContador(contador);
        Runnable tarea2 = new HiloContador(contador);

        Thread t1 = new Thread(tarea1);
        Thread t2 = new Thread(tarea2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final contador: " + contador.getContador());
    }
}