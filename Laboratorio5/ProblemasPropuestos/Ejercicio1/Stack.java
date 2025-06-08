package ProblemasPropuestos.Ejercicio1;

public class Stack<E> {
    private Nodo<E> cima;

    public Stack(){
        cima = null;
    }

    public E push(E dato) {
        Nodo<E> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        return dato;
    }

    public void printStack() {
        Nodo<E> actual = cima;
        System.out.print("Pila: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
