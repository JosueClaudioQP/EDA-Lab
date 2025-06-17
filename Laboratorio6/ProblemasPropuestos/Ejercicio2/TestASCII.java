package ProblemasPropuestos.Ejercicio2;
import ProblemasPropuestos.Ejercicio1.BST;
import java.util.Scanner;

public class TestASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST<Character> let = new BST<>();

        System.out.print("Ingresar Palabra: ");
        String palabra = sc.nextLine();

        for(int i = 0; i < palabra.length(); i++){
            char p = palabra.charAt(i);
            let.insert(p);
        }

        System.out.println(let.isEmpty() + "\n");

        int opcion;
        do {
            System.out.println("\n--- MENU BST ---");
            System.out.println("1. Mostrar Árbol (Inorden)");
            System.out.println("2. Mostrar Árbol (Postorden)");
            System.out.println("3. Mostrar Árbol (Preorden)");
            System.out.println("4. Eliminar un nodo");
            System.out.println("5. Encontrar un nodo");
            System.out.println("6. Encontrar nodo mínimo");
            System.out.println("7. Encontrar nodo máximo");
            System.out.println("8. Encontrar el sucesor de un nodo");
            System.out.println("9. Destruir árbol");
            System.out.println("10. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Recorrido Inorden: ");
                    let.InOrder();
                    System.out.println();
                    break;
                case 2:
                    System.err.println("Recorrido PostOrden: ");
                    let.PostOrder();
                    break;
                case 3:
                    System.err.println("Recorrido PreOrden: ");
                    let.PreOrder();
                    break;
                case 4:
                    System.out.print("Ingresa el valor a eliminar: ");
                    String valor = sc.nextLine();
                    char letra = valor.charAt(0);
                    let.remove(letra);
                    System.out.println("Nodo eliminado (si existía).");
                    break;
                case 5:
                    System.out.print("Ingresar el valor a buscar: ");
                    String valorIng = sc.nextLine();
                    let.search(valorIng.charAt(0));
                    break;
                case 6:
                    System.out.print("El nodo mínimo es: ");
                    let.Min();
                    break;
                case 7:
                    System.out.print("El nodo máximo es: ");
                    let.Max();
                    break;
                case 8:
                    System.out.print("Ingresa el nodo: ");
                    String sucess = sc.nextLine();
                    let.Sucesor(sucess.charAt(0));
                    break;
                case 9:
                    System.out.print("Ingresa el nodo: ");
                    String predecesor = sc.nextLine();
                    let.Sucesor(predecesor.charAt(0));
                    break;
                case 10:
                    let.destroy();
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 11);


        sc.close();

    }
}
