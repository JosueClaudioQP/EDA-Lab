package EjerciciosPropuestos.HashOpened;

import java.util.Scanner;

public class TestHashAbierto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashAbierto<Integer, String> hash = new HashAbierto<>(5);

        int opcion;
        do {
            System.out.println("\n===== MENÚ HASH ABIERTO =====");
            System.out.println("1. Agregar (clave, valor)");
            System.out.println("2. Buscar por clave");
            System.out.println("3. Eliminar por clave");
            System.out.println("4. Mostrar tabla");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese clave (número entero): ");
                    int claveInsertar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese valor (texto): ");
                    String valor = scanner.nextLine();
                    hash.agregar(claveInsertar, valor);
                    System.out.println("Elemento agregado.");
                    break;

                case 2:
                    System.out.print("Ingrese clave a buscar: ");
                    int claveBuscar = scanner.nextInt();
                    String resultado = hash.buscar(claveBuscar);
                    if (resultado != null) {
                        System.out.println("Valor encontrado: " + resultado);
                    } else {
                        System.out.println("Clave no encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese clave a eliminar: ");
                    int claveEliminar = scanner.nextInt();
                    boolean eliminado = hash.eliminar(claveEliminar);
                    System.out.println(eliminado ? "Elemento eliminado." : "Clave no encontrada.");
                    break;

                case 4:
                    hash.mostrarTabla();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
