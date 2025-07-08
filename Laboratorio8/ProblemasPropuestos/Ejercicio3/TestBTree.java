package ProblemasPropuestos.Ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import ProblemasPropuestos.Ejercicio4.BTreeVisualizer;

public class TestBTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BTree<Integer> bTree = null;

        System.out.println("--- CREADOR DE ÁRBOL-B ---");
        while (bTree == null) {
            try {
                System.out.print("Introduce el grado mínimo (t) del árbol (t >= 2): ");
                int t = scanner.nextInt();
                bTree = new BTree<>(t);
                System.out.println("Árbol-B de grado " + t + " creado.");
                if(t == 2){
                    int[] nums = {100,200,300,400,500,50,25,350,375,360,355,150,175,120,190};
                    for(int i = 0; i < nums.length; i++){
                        bTree.insert(nums[i]);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número entero.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }

        int choice;
        do {
            printMenu();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Introduce el valor a insertar: ");
                        bTree.insert(scanner.nextInt());
                        System.out.println("Valor insertado.");
                        break;
                    case 2:
                        System.out.print("Introduce el valor a eliminar: ");
                        bTree.remove(scanner.nextInt());
                        break;
                    case 3:
                        System.out.print("Introduce el valor a buscar: ");
                        int valBusqueda = scanner.nextInt();
                        if (bTree.search(valBusqueda) != null) {
                            System.out.println("El valor " + valBusqueda + " SÍ se encuentra en el árbol.");
                        } else {
                            System.out.println("El valor " + valBusqueda + " NO se encuentra en el árbol.");
                        }
                        break;
                    case 4:
                        System.out.println("Árbol actual (vista estructurada):");
                        bTree.writeTree();
                        break;
                    case 5:
                        System.out.println("Árbol actual (vista por niveles):");
                        System.out.println(bTree.toString());
                        break;
                    case 6:
                        Integer min = bTree.getMin();
                        if (min != null) System.out.println("Valor mínimo: " + min);
                        else System.out.println("El árbol está vacío.");
                        break;
                    case 7:
                        Integer max = bTree.getMax();
                        if (max != null) System.out.println("Valor máximo: " + max);
                        else System.out.println("El árbol está vacío.");
                        break;
                    case 8:
                        System.out.print("Introduce la clave para buscar su predecesor: ");
                        Integer predKey = scanner.nextInt();
                        Integer predecesor = bTree.predecessor(predKey);
                        if(predecesor != null) System.out.println("El predecesor de " + predKey + " es: " + predecesor);
                        else System.out.println("No se encontró un predecesor para " + predKey);
                        break;
                    case 9:
                        System.out.print("Introduce la clave para buscar su sucesor: ");
                        Integer succKey = scanner.nextInt();
                        Integer sucesor = bTree.successor(succKey);
                        if(sucesor != null) System.out.println("El sucesor de " + succKey + " es: " + sucesor);
                        else System.out.println("No se encontró un sucesor para " + succKey);
                        break;
                    case 10:
                        bTree.destroy();
                        System.out.println("El árbol ha sido vaciado.");
                        break;
                    case 11:
                        System.out.println("¿El árbol está vacío? " + (bTree.isEmpty() ? "Sí" : "No"));
                        break;
                    case 12: // Nueva opción
                        if (bTree.isEmpty()) {
                            System.out.println("El árbol está vacío, no hay nada que graficar.");
                        } else {
                            // Es una buena práctica ejecutar componentes Swing en el Event Dispatch Thread
                            final BTree<Integer> treeToVisualize = bTree;
                            SwingUtilities.invokeLater(() -> new BTreeVisualizer<>(treeToVisualize.getRoot()));
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
                scanner.next(); // Limpiar el buffer
                choice = -1; // Forzar a que el bucle continúe
            }
            System.out.println(); // Línea en blanco para separar
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- MENÚ DE OPERACIONES DEL ÁRBOL-B ---");
        System.out.println("1. Insertar un valor");
        System.out.println("2. Eliminar un valor");
        System.out.println("3. Buscar un valor");
        System.out.println("4. Mostrar árbol (estructurado - writeTree)");
        System.out.println("5. Mostrar árbol (por niveles - toString)");
        System.out.println("6. Obtener valor mínimo");
        System.out.println("7. Obtener valor máximo");
        System.out.println("8. Obtener predecesor de una clave");
        System.out.println("9. Obtener sucesor de una clave");
        System.out.println("10. Vaciar el árbol (destroy)");
        System.out.println("11. Comprobar si está vacío (isEmpty)");
        System.out.println("12. Graficar Árbol (Swing)");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
}