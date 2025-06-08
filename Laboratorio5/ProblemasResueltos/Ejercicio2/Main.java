package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        QueueList<Integer> cola = new QueueList<>(8);

        for (int i = 1; i <= 8; i++) {
            cola.enqueue(i);
        }

        cola.mostrar();
        System.out.println("¿Está llena? " + cola.isFull());

        cola.front();
        cola.back();

        cola.enqueue(9);
        cola.mostrar();

        cola.dequeue();
        cola.dequeue();

        cola.mostrar();
        System.out.println("¿Está llena? " + cola.isFull());


        System.out.println("¿Está vacía? " + cola.isEmpty());

        cola.destroyQueue();
        cola.mostrar();
    }
}
