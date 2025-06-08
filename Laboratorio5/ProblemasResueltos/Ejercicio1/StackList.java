package Ejercicio1;

public class StackList<T> implements Pila<T>{
    private Nodo<T> cima;

    public StackList(){
        cima = null;
    }

    public void push(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.next = cima;
        cima = nuevoNodo;
    }

    public T pop(){
        if (isEmpty()) {
            System.out.println("La pila está vacía, no se puede hacer pop.");
            return null;
        }
        T dato = cima.dato;
        cima = cima.next;
        return dato;
    }

    public void top(){
        if (isEmpty()) {
            System.out.println("La pila está vacía, no se puede hacer pop.");
        } else {
            System.out.println("Cima: " + cima.dato);
        }
    }

    public void destroyStack() {
        cima = null;
        System.out.println("Pila destruida (vacía).");
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public boolean isFull() {
        return false;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return;
        }
        Nodo<T> actual = cima;
        System.out.print("Pila: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.next;
        }
        System.out.println();
    }
}
