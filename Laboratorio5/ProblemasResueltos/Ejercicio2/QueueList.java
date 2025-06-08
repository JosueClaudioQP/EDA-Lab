package Ejercicio2;

public class QueueList<T> implements Cola<T>{
    private Nodo<T> frente;
    private Nodo<T> finCola;
    private int tamaño;
    private int capacidad;

    public QueueList() {
        this.frente = null;
        this.finCola = null;
        this.tamaño = 0;
        this.capacidad = Integer.MAX_VALUE;
    }

    public QueueList(int capacidad){
        this();
        this.capacidad = capacidad;
    }

    public T enqueue(T elem) {
        if (isFull()) {
            System.out.println("La cola está llena, no se puede encolar.");
            return null;
        }
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (isEmpty()) {
            frente = finCola = nuevoNodo;
        } else {
            finCola.next = nuevoNodo;
            finCola = nuevoNodo;
        }
        tamaño++;
        return elem;
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía, no se puede desencolar.");
            return;
        }
        frente = frente.next;
        tamaño--;
        if (frente == null) {
            finCola = null;
        }
    }

    public void front() {
        if (isEmpty()) {
            System.out.println("La cola está vacía, no hay frente.");
        } else {
            System.out.println("Frente: " + frente.dato);
        }
    }

    public void back() {
        if (isEmpty()) {
            System.out.println("La cola está vacía, no hay final.");
        } else {
            System.out.println("Final: " + finCola.dato);
        }
    }

    public void destroyQueue() {
        frente = null;
        finCola = null;
        tamaño = 0;
        System.out.println("Cola destruida (vacía).");
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public boolean isFull() {
        return tamaño >= capacidad;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo<T> actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.next;
        }
        System.out.println();
    }
}
