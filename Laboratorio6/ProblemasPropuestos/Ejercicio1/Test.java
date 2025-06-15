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

        System.out.println(arbol.isEmpty() + "\n");

        int opcion;
        do {
            System.out.println("\n--- MENU BST ---");
            System.out.println("1. Mostrar Árbol (Inorden)");
            System.out.println("2. Mostrar Árbol (Postorden)");
            System.out.println("3. Mostrar Árbol (Preorden)");
            System.out.println("4. Eliminar un nodo");
            System.out.println("5. Encontrar un nodo");
            System.out.println("6. Destruir árbol");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Recorrido Inorden: ");
                    arbol.InOrder();
                    System.out.println();
                    break;
                case 2:
                    System.err.println("Recorrido PostOrden: ");
                    arbol.PostOrder();
                    break;
                case 3:
                    System.err.println("Recorrido PreOrden: ");
                    arbol.PreOrder();
                    break;
                case 4:
                    System.out.print("Ingresa el valor a eliminar: ");
                    int valor = sc.nextInt();
                    arbol.remove(valor);
                    System.out.println("Nodo eliminado (si existía).");
                    break;
                case 5:
                    System.out.println("Ingresar el valor a buscar: ");
                    int valorIng = sc.nextInt();
                    arbol.search(valorIng);
                    break;
                case 6:
                    arbol.destroy();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);

        sc.close();
    }
}
