package ProblemasPropuestos.Ejercicio7;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import ProblemasPropuestos.Ejercicio8.BPlusTreeVisualizer;

public class TestBPlusTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BPlusTree<Integer> bPlusTree = null;

        System.out.println("--- CREADOR DE ÁRBOL-B+ ---");
        while (bPlusTree == null) {
            try {
                System.out.print("Introduce el orden (m) del árbol (m >= 3): ");
                int m = scanner.nextInt();
                bPlusTree = new BPlusTree<>(m);
                System.out.println("Árbol-B+ de orden " + m + " creado.");
                if(m == 5){
                    int[] nums = {100,200,300,400,500,50,25,350,375,360,355,150,175,120,190};
                    for(int i = 0; i < nums.length; i++){
                        bPlusTree.insert(nums[i]);
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
                        bPlusTree.insert(scanner.nextInt());
                        System.out.println("Valor insertado.");
                        break;
                    case 2:
                        System.out.print("Introduce el valor a eliminar: ");
                        bPlusTree.remove(scanner.nextInt());
                        break;
                    case 3:
                        System.out.print("Introduce el valor a buscar: ");
                        int valBusqueda = scanner.nextInt();
                        if (bPlusTree.search(valBusqueda)) {
                            System.out.println("El valor " + valBusqueda + " SÍ se encuentra en el árbol.");
                        } else {
                            System.out.println("El valor " + valBusqueda + " NO se encuentra en el árbol.");
                        }
                        break;
                    case 4:
                        System.out.println("Árbol actual (vista por niveles):");
                        bPlusTree.writeTree();
                        break;
                    case 5:
                        System.out.println("Árbol actual (recorrido de hojas - toString):");
                        System.out.println(bPlusTree.toString());
                        break;
                    case 6:
                        Integer min = bPlusTree.getMin();
                        if (min != null) System.out.println("Valor mínimo: " + min);
                        else System.out.println("El árbol está vacío.");
                        break;
                    case 7:
                        Integer max = bPlusTree.getMax();
                        if (max != null) System.out.println("Valor máximo: " + max);
                        else System.out.println("El árbol está vacío.");
                        break;
                    case 8:
                        System.out.print("Introduce la clave de inicio del rango: ");
                        int start = scanner.nextInt();
                        System.out.print("Introduce la clave de fin del rango: ");
                        int end = scanner.nextInt();
                        List<Integer> range = bPlusTree.rangeSearch(start, end);
                        System.out.println("Valores en el rango [" + start + ", " + end + "]: " + range);
                        break;
                    case 9:
                        bPlusTree.destroy();
                        System.out.println("El árbol ha sido vaciado.");
                        break;
                    case 11: // Nueva opción
                        if (bPlusTree.isEmpty()) {
                            System.out.println("El árbol está vacío, no hay nada que graficar.");
                        } else {
                            final BPlusTree<Integer> treeToVisualize = bPlusTree;
                            SwingUtilities.invokeLater(() -> new BPlusTreeVisualizer<>(treeToVisualize.getRoot()));
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
        System.out.println("--- MENÚ DE OPERACIONES DEL ÁRBOL-B+ ---");
        System.out.println("1. Insertar un valor");
        System.out.println("2. Eliminar un valor");
        System.out.println("3. Buscar un valor");
        System.out.println("4. Mostrar árbol (por niveles - writeTree)");
        System.out.println("5. Mostrar recorrido de hojas (toString)");
        System.out.println("6. Obtener valor mínimo");
        System.out.println("7. Obtener valor máximo");
        System.out.println("8. Búsqueda por rango");
        System.out.println("9. Vaciar el árbol (destroy)");
        System.out.println("10. Comprobar si está vacío (isEmpty)");
        System.out.println("11. Graficar Árbol (Swing)");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
}