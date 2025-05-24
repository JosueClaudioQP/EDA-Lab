package Laboratorio3;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> alumnos = new ArrayList<String>();
        ArrayList<Integer> notas = new ArrayList<Integer>();
        alumnos.add("MARIA");
        alumnos.add("DIEGO");
        alumnos.add("RENE");
        alumnos.add("ALONSO");
        System.out.println(alumnos.hashCode());
        System.out.println(alumnos.isEmpty());
        System.out.println(alumnos.size());

        Iterator<String> itA = alumnos.iterator();
            while (itA.hasNext()) {
                System.out.println(itA.next());
            }

        MyList<String> lista = new MyList<>();

        // Agregar elementos
        lista.add("Uno");
        lista.add("Dos");
        lista.add("Tres");
        System.out.println("Lista después de agregar elementos: " + lista);

        // Obtener elementos
        System.out.println("Elemento en la posición 1: " + lista.get(1)); // Debe ser "Dos"

        // Insertar en una posición específica
        lista.add(1, "Insertado");
        System.out.println("Lista después de insertar en índice 1: " + lista);

        // Eliminar un elemento
        String eliminado = lista.remove(2);
        System.out.println("Elemento eliminado en índice 2: " + eliminado);
        System.out.println("Lista después de eliminar: " + lista);

        // Buscar elementos
        System.out.println("Contiene 'Uno': " + lista.contains("Uno")); // true
        System.out.println("Índice de 'Tres': " + lista.indexOf("Tres")); // 2

        // Reemplazar un elemento
        lista.set(0, "Primero");
        System.out.println("Lista después de reemplazar índice 0: " + lista);

        // Tamaño de la lista
        System.out.println("Tamaño actual: " + lista.size());

        // Vaciar lista
        lista.clear();
        System.out.println("Lista después de limpiar: " + lista);
        System.out.println("¿Está vacía?: " + lista.isEmpty());
    }
}