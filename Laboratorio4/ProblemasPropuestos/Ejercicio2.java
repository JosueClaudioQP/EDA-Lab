package Laboratorio4.ProblemasPropuestos;

public class Ejercicio2 {

    // Clase Nodo para lista circular
    static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;

        public Nodo(E dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Lista circular genérica
    static class LinkedList<E> {
        private Nodo<E> cabeza;

        // Insertar al final
        public void insertar(E valor) {
            Nodo<E> nuevo = new Nodo<>(valor);
            if (cabeza == null) {
                cabeza = nuevo;
                cabeza.siguiente = cabeza; // Enlaza a sí misma
            } else {
                Nodo<E> actual = cabeza;
                while (actual.siguiente != cabeza) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
                nuevo.siguiente = cabeza;
            }
        }

        // Imprimir lista circular
        public void imprimir() {
            if (cabeza == null) {
                System.out.println("Lista vacía");
                return;
            }

            Nodo<E> actual = cabeza;
            do {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            } while (actual != cabeza);
            System.out.println();
        }

        // Método genérico: obtener cabeza
        public E obtenerPrimero() {
            return cabeza != null ? cabeza.dato : null;
        }
    }

    // Método principal
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        for (int i = 1; i <= 12; i++) {
            lista.insertar(i);
        }

        System.out.println("Lista circular (1 al 12):");
        lista.imprimir();

        System.out.println("Primer elemento (método genérico): " + lista.obtenerPrimero());
    }
}
