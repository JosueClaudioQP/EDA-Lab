package ProblemasPropuestos.Ejercicio2;

public class Stack<E> {
    private Nodo<E> cima;
    private int tamaño;
    private final int capacidad;

    public Stack() {
        this.capacidad = Integer.MAX_VALUE;
        this.cima = null;
        this.tamaño = 0;
    }

    public Stack(int capacidad) {
        this.capacidad = capacidad;
        this.cima = null;
        this.tamaño = 0;
    }

    public E push(E dato) {
        if (isFull()) {
            System.out.println("La pila está llena. No se puede insertar " + dato);
            return null;
        }
        Nodo<E> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamaño++;
        return dato;
    }

    public E pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía. No se puede desapilar.");
            return null;
        }
        E dato = cima.dato;
        cima = cima.siguiente;
        tamaño--;
        return dato;
    }

    public void top() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
        } else {
            System.out.println("Cima: " + cima.dato);
        }
    }

    public void destroyStack() {
        cima = null;
        tamaño = 0;
        System.out.println("Pila destruida (vacía).");
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public boolean isFull() {
        return tamaño >= capacidad;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return;
        }
        Nodo<E> actual = cima;
        System.out.print("Pila: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
