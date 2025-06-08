package ProblemasPropuestos.Ejercicio3;

public class Queue<E> {
    private Nodo<E> frente;
    private Nodo<E> fin;

    public Queue() {
        frente = null;
        fin = null;
    }

    public E enqueue(E dato) {
        Nodo<E> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        return dato;
    }

    public void printQueue() {
        Nodo<E> actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return frente == null;
    }
}