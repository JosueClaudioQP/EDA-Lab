package Laboratorio3;

import java.util.Scanner;

public class TestOperador {
    static <T extends Number> double suma(T valor1, T valor2) {
        return valor1.doubleValue() + valor2.doubleValue();
    }

    static <T extends Number> double resta(T valor1, T valor2) {
        return valor1.doubleValue() - valor2.doubleValue();
    }

    static <T extends Number> double producto(T valor1, T valor2) {
        return valor1.doubleValue() * valor2.doubleValue();
    }

    static <T extends Number> double division(T valor1, T valor2) {
        if (valor2.doubleValue() == 0) {
            System.out.println("Error: División por cero.");
            return Double.NaN;
        }
        return valor1.doubleValue() / valor2.doubleValue();
    }

    static <T extends Number> double potencia(T valor1, T valor2) {
        return Math.pow(valor1.doubleValue(), valor2.doubleValue());
    }

    static <T extends Number> double raizCuadrada(T valor) {
        if (valor.doubleValue() < 0) {
            System.out.println("Error: Raíz cuadrada de número negativo.");
            return Double.NaN;
        }
        return Math.sqrt(valor.doubleValue());
    }

    static <T extends Number> double raizCubica(T valor) {
        return Math.cbrt(valor.doubleValue());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Raíz Cúbica");
            System.out.println("8. Salir del Programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 7) {
                System.out.print("¿Tipo de dato? (1: Integer, 2: Double): ");
                int tipo = scanner.nextInt();

                switch (opcion) {
                    case 1, 2, 3, 4, 5 -> {
                        if (tipo == 1) {
                            System.out.print("Ingrese valor 1: ");
                            Integer i1 = scanner.nextInt();
                            System.out.print("Ingrese valor 2: ");
                            Integer i2 = scanner.nextInt();
                            ejecutarOperacion(opcion, i1, i2);
                        } else {
                            System.out.print("Ingrese valor 1: ");
                            Double d1 = scanner.nextDouble();
                            System.out.print("Ingrese valor 2: ");
                            Double d2 = scanner.nextDouble();
                            ejecutarOperacion(opcion, d1, d2);
                        }
                    }
                    case 6, 7 -> {
                        if (tipo == 1) {
                            System.out.print("Ingrese el valor: ");
                            Integer i = scanner.nextInt();
                            ejecutarRaiz(opcion, i);
                        } else {
                            System.out.print("Ingrese el valor: ");
                            Double d = scanner.nextDouble();
                            ejecutarRaiz(opcion, d);
                        }
                    }
                }
            } else if (opcion == 8) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción no válida.");
            }

        } while (opcion != 8);

        scanner.close();
    }

    // Método auxiliar para operaciones con dos operandos
    static <T extends Number> void ejecutarOperacion(int opcion, T valor1, T valor2) {
        double resultado = switch (opcion) {
            case 1 -> suma(valor1, valor2);
            case 2 -> resta(valor1, valor2);
            case 3 -> producto(valor1, valor2);
            case 4 -> division(valor1, valor2);
            case 5 -> potencia(valor1, valor2);
            default -> 0;
        };
        System.out.println("Resultado: " + resultado);
    }

    // Método auxiliar para operaciones con un solo operando
    static <T extends Number> void ejecutarRaiz(int opcion, T valor) {
        double resultado = (opcion == 6) ? raizCuadrada(valor) : raizCubica(valor);
        System.out.println("Resultado: " + resultado);
    }
}
