package ProblemasPropuestos.Ejercicio4;

public class Queue<E> {
    private Nodo<E> frente;
    private Nodo<E> fin;
    private int tamaño;
    private final int capacidad;

    public Queue() {
        this.capacidad = Integer.MAX_VALUE;
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    public Queue(int capacidad) {
        this.capacidad = capacidad;
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    public E enqueue(E dato) {
        if (isFull()) {
            System.out.println("La cola está llena. No se puede insertar " + dato);
            return null;
        }
        Nodo<E> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamaño++;
        return dato;
    }

    public E dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede eliminar.");
            return null;
        }
        E dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamaño--;
        return dato;
    }

    public void destroyQueue() {
        frente = null;
        fin = null;
        tamaño = 0;
        System.out.println("Cola destruida (vacía).");
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public boolean isFull() {
        return tamaño >= capacidad;
    }

    public void front() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Frente: " + frente.dato);
        }
    }

    public void back() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Último: " + fin.dato);
        }
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo<E> actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
