package Laboratorio4.ProblemasPropuestos;
import java.util.LinkedList;

public class Ejercicio4 {

    public static <E> E obtenerPrimero(LinkedList<E> lista) {
        return lista.isEmpty() ? null : lista.getFirst();
    }

    public static <E> void imprimirCircular(LinkedList<E> lista, int repeticiones) {
        if (lista.isEmpty()) {
            System.out.println("Lista vacía");
            return;
        }

        int size = lista.size();
        for (int i = 0; i < repeticiones; i++) {
            System.out.print(lista.get(i % size) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();

        for (int i = 1; i <= 12; i++) {
            lista.add(i);
        }

        System.out.println("Lista circular simulada (1 al 12, dos vueltas):");
        imprimirCircular(lista, 24);

        System.out.println("Primer elemento (método genérico): " + obtenerPrimero(lista));
    }
}

