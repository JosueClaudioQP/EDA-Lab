package ProblemasPropuestos.Ejercicio4;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> cola = new Queue<>(10);
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Menú de Cola ===");
            System.out.println("1. Encolar (enqueue)");
            System.out.println("2. Desencolar (dequeue)");
            System.out.println("3. Ver frente");
            System.out.println("4. Ver último");
            System.out.println("5. ¿Está vacía?");
            System.out.println("6. ¿Está llena?");
            System.out.println("7. Mostrar cola");
            System.out.println("8. Vaciar cola");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese número a encolar: ");
                    int dato = sc.nextInt();
                    cola.enqueue(dato);
                    break;
                case 2:
                    cola.dequeue();
                    break;
                case 3:
                    cola.front();
                    break;
                case 4:
                    cola.back();
                    break;
                case 5:
                    System.out.println("¿Vacía?: " + cola.isEmpty());
                    break;
                case 6:
                    System.out.println("¿Llena?: " + cola.isFull());
                    break;
                case 7:
                    cola.printQueue();
                    break;
                case 8:
                    cola.destroyQueue();
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
