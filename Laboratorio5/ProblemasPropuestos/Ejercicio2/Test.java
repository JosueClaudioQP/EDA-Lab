package ProblemasPropuestos.Ejercicio2;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>(10);
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Menú de Pila ===");
            System.out.println("1. Apilar (push)");
            System.out.println("2. Desapilar (pop)");
            System.out.println("3. Ver cima (top)");
            System.out.println("4. ¿Está vacía?");
            System.out.println("5. ¿Está llena?");
            System.out.println("6. Mostrar pila");
            System.out.println("7. Vaciar pila");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese número a apilar: ");
                    int dato = sc.nextInt();
                    pila.push(dato);
                    break;
                case 2:
                    pila.pop();
                    break;
                case 3:
                    pila.top();
                    break;
                case 4:
                    System.out.println("¿Vacía?: " + pila.isEmpty());
                    break;
                case 5:
                    System.out.println("¿Llena?: " + pila.isFull());
                    break;
                case 6:
                    pila.printStack();
                    break;
                case 7:
                    pila.destroyStack();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}
