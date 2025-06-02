package Laboratorio4.ProblemasPropuestos;
import java.util.Scanner;

public class Ejercicio5 {

    static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;

        public Nodo(E dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    static class LinkedList<E> {
        private Nodo<E> cabeza;

        public void insert(E dato) {
            Nodo<E> nuevo = new Nodo<>(dato);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo<E> actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
        }

        public void addFirst(E dato) {
            Nodo<E> nuevo = new Nodo<>(dato);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        public void addLast(E dato) {
            insert(dato);
        }

        public void deleteByKey(E key) {
            if (cabeza == null) return;

            if (cabeza.dato.equals(key)) {
                cabeza = cabeza.siguiente;
                return;
            }

            Nodo<E> actual = cabeza;
            while (actual.siguiente != null && !actual.siguiente.dato.equals(key)) {
                actual = actual.siguiente;
            }

            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
            }
        }

        public void deleteAtPosition(int posicion) {
            if (posicion < 0 || cabeza == null) return;

            if (posicion == 0) {
                cabeza = cabeza.siguiente;
                return;
            }

            Nodo<E> actual = cabeza;
            for (int i = 0; actual != null && i < posicion - 1; i++) {
                actual = actual.siguiente;
            }

            if (actual != null && actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
            }
        }

        public void removeFirst() {
            if (cabeza != null) {
                cabeza = cabeza.siguiente;
            }
        }

        public void removeLast() {
            if (cabeza == null || cabeza.siguiente == null) {
                cabeza = null;
                return;
            }

            Nodo<E> actual = cabeza;
            while (actual.siguiente.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = null;
        }

        public void printList() {
            Nodo<E> actual = cabeza;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        public int size() {
            int count = 0;
            Nodo<E> actual = cabeza;
            while (actual != null) {
                count++;
                actual = actual.siguiente;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        for (int i = 1; i <= 10; i++) {
            lista.insert(i);
        }

        do {
            System.out.println("\n--- Menú Lista Enlazada Simple ---");
            System.out.println("1. Mostrar lista");
            System.out.println("2. Insertar al inicio");
            System.out.println("3. Insertar al final");
            System.out.println("4. Eliminar por valor");
            System.out.println("5. Eliminar por posición");
            System.out.println("6. Eliminar primero");
            System.out.println("7. Eliminar último");
            System.out.println("8. Tamaño de la lista");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> lista.printList();
                case 2 -> {
                    System.out.print("Dato a insertar al inicio: ");
                    int valor = scanner.nextInt();
                    lista.addFirst(valor);
                }
                case 3 -> {
                    System.out.print("Dato a insertar al final: ");
                    int valor = scanner.nextInt();
                    lista.addLast(valor);
                }
                case 4 -> {
                    System.out.print("Valor a eliminar: ");
                    int valor = scanner.nextInt();
                    lista.deleteByKey(valor);
                }
                case 5 -> {
                    System.out.print("Posición a eliminar: ");
                    int pos = scanner.nextInt();
                    lista.deleteAtPosition(pos);
                }
                case 6 -> lista.removeFirst();
                case 7 -> lista.removeLast();
                case 8 -> System.out.println("Tamaño: " + lista.size());
                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 9);

        scanner.close();
    }
}

