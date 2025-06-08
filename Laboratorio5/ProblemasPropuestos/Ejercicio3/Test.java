package ProblemasPropuestos.Ejercicio3;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> cola = new Queue<>();

        for (int i = 1; i <= 10; i++) {
            cola.enqueue(i);
        }

        cola.printQueue();
    }
}
