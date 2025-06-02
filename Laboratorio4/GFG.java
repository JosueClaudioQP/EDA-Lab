package Laboratorio4;
import java.util.*;

public class GFG {
    public static void main(String[] args) {
        // Creando el objeto de la
        // clase lista enlazada
        LinkedList<String> ll = new LinkedList<String>();
        // Añadido de elementos a la lista enlazada
        ll.add("A");
        ll.add("B");
        ll.addLast("C");
        ll.addFirst("D");
        ll.add(2, "E");
        System.out.println(ll);
        ll.remove("B");
        ll.remove(3);
        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);
    }
}
