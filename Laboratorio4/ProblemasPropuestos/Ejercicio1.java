package Laboratorio4.ProblemasPropuestos;

public class Ejercicio1 {

    // Clase Nodo doblemente enlazado
    static class NodoDoble<E> {
        E dato;
        NodoDoble<E> anterior;
        NodoDoble<E> siguiente;

        public NodoDoble(E dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    // Clase LinkedList genérica
    static class LinkedList<E> {
        private NodoDoble<E> cabeza;
        private NodoDoble<E> cola;

        // Insertar al final
        public void insertar(E valor) {
            NodoDoble<E> nuevo = new NodoDoble<>(valor);
            if (cabeza == null) {
                cabeza = cola = nuevo;
            } else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }
        }

        // Imprimir la lista
        public void imprimir() {
            NodoDoble<E> actual = cabeza;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        // Método genérico de ejemplo
        public E obtenerPrimero() {
            return cabeza != null ? cabeza.dato : null;
        }
    }

    // Clase principal
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            lista.insertar(i);
        }

        System.out.println("Lista doblemente enlazada (1 al 10):");
        lista.imprimir();

        System.out.println("Primer elemento (método genérico): " + lista.obtenerPrimero());
    }
}
