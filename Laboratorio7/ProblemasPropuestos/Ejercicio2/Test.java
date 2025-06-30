package ProblemasPropuestos.Ejercicio2;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        int opc, valor;

        for(int i = 0; i < 15; i++){
            tree.insert(i+1);
        }

        do {
            System.out.println("\n--- MENÚ ÁRBOL AVL ---");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Buscar");
            System.out.println("4. Mostrar Min y Max");
            System.out.println("5. Predecesor / Sucesor");
            System.out.println("6. Recorrido InOrder");
            System.out.println("7. Recorrido PreOrder");
            System.out.println("8. Recorrido PostOrder");
            System.out.println("9. Vaciar árbol");
            System.out.println("10. ¿Está vacío?");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    tree.insert(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Valor a eliminar: ");
                    tree.remove(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Valor a buscar: ");
                    System.out.println(tree.search(sc.nextInt()) ? "Encontrado" : "No encontrado");
                    break;
                case 4:
                    System.out.println("Mínimo: " + tree.min());
                    System.out.println("Máximo: " + tree.max());
                    break;
                case 5:
                    System.out.print("Valor de referencia: ");
                    valor = sc.nextInt();
                    System.out.println("Predecesor: " + tree.predecesor(valor));
                    System.out.println("Sucesor: " + tree.sucesor(valor));
                    break;
                case 6:
                    tree.inOrder();
                    break;
                case 7:
                    tree.preOrder();
                    break;
                case 8:
                    tree.postOrder();
                    break;
                case 9:
                    tree.destroy();
                    System.out.println("Árbol eliminado.");
                    break;
                case 10:
                    System.out.println(tree.isEmpty() ? "Árbol vacío" : "Árbol no vacío");
                    break;
            }
        } while (opc != 0);
        sc.close();
    }
}
