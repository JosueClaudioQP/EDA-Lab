package Laboratorio4.ProblemasPropuestos;
import java.util.LinkedList;

public class Ejercicio3 {

    public static <E> E obtenerPrimero(LinkedList<E> lista) {
        return lista.isEmpty() ? null : lista.getFirst();
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            lista.add(i);
        }

        System.out.println("Lista doblemente enlazada (1 al 10) usando java.util.LinkedList:");
        for (Integer num : lista) {
            System.out.print(num + " ");
        }

        System.out.println("\nPrimer elemento (método genérico): " + obtenerPrimero(lista));
    }
}

