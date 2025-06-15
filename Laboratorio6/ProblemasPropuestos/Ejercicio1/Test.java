package ProblemasPropuestos.Ejercicio1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST<Integer> arbol = new BST<>();

        arbol.insert(50);
        arbol.insert(30);
        arbol.insert(70);
        arbol.insert(20);
        arbol.insert(40);
        arbol.insert(60);
        arbol.insert(80);

        int opcion;
        do {
            System.out.println("\n--- MENU BST ---");
            System.out.println("1. Mostrar Árbol (Inorden)");
            System.out.println("2. Eliminar un nodo");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Recorrido Inorden: ");
                    arbol.InOrder();
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Ingresa el valor a eliminar: ");
                    int valor = sc.nextInt();
                    arbol.remove(valor);
                    System.out.println("Nodo eliminado (si existía).");
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);

        sc.close();
    }
}
