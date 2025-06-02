package Laboratorio4;
import java.util.Scanner;

public class Ejercicio7 {

    static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;

        public Nodo(E dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    static class LinkedList<E> {
        private Nodo<E> ultimo;

        public void insert(E dato) {
            Nodo<E> nuevo = new Nodo<>(dato);
            if (ultimo == null) {
                nuevo.siguiente = nuevo;
                ultimo = nuevo;
            } else {
                nuevo.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
        }

        public void addFirst(E dato) {
            Nodo<E> nuevo = new Nodo<>(dato);
            if (ultimo == null) {
                nuevo.siguiente = nuevo;
                ultimo = nuevo;
            } else {
                nuevo.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevo;
            }
        }

        public void addLast(E dato) {
            insert(dato);
        }

        public void deleteByKey(E key) {
            if (ultimo == null) return;

            Nodo<E> actual = ultimo.siguiente;
            Nodo<E> anterior = ultimo;
            do {
                if (actual.dato.equals(key)) {
                    if (actual == ultimo && actual == ultimo.siguiente) {
                        ultimo = null;
                    } else {
                        anterior.siguiente = actual.siguiente;
                        if (actual == ultimo) {
                            ultimo = anterior;
                        }
                    }
                    return;
                }
                anterior = actual;
                actual = actual.siguiente;
            } while (actual != ultimo.siguiente);
        }

        public void deleteAtPosition(int pos) {
            if (ultimo == null || pos < 0) return;

            Nodo<E> actual = ultimo.siguiente;
            Nodo<E> anterior = ultimo;

            for (int i = 0; i < pos; i++) {
                anterior = actual;
                actual = actual.siguiente;
                if (actual == ultimo.siguiente) return;
            }

            if (actual == ultimo && actual == ultimo.siguiente) {
                ultimo = null;
            } else {
                anterior.siguiente = actual.siguiente;
                if (actual == ultimo) {
                    ultimo = anterior;
                }
            }
        }

        public void removeFirst() {
            if (ultimo == null) return;

            Nodo<E> primero = ultimo.siguiente;
            if (primero == ultimo) {
                ultimo = null;
            } else {
                ultimo.siguiente = primero.siguiente;
            }
        }

        public void removeLast() {
            if (ultimo == null) return;

            Nodo<E> actual = ultimo.siguiente;
            Nodo<E> anterior = ultimo;

            if (actual == ultimo) {
                ultimo = null;
                return;
            }

            while (actual.siguiente != ultimo.siguiente) {
                anterior = actual;
                actual = actual.siguiente;
            }

            anterior.siguiente = ultimo.siguiente;
            ultimo = anterior;
        }

        public int size() {
            if (ultimo == null) return 0;
            int count = 1;
            Nodo<E> actual = ultimo.siguiente;
            while (actual != ultimo) {
                count++;
                actual = actual.siguiente;
            }
            return count;
        }

        public void printList() {
            if (ultimo == null) {
                System.out.println("Lista vacía");
                return;
            }
            Nodo<E> actual = ultimo.siguiente;
            do {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            } while (actual != ultimo.siguiente);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        for (int i = 1; i <= 12; i++) {
            lista.insert(i);
        }

        do {
            System.out.println("\n--- Menú Lista Circular ---");
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
