package Laboratorio2.EjerciciosPropuestos;

import java.util.Scanner;

public class Propuesto6 {
    public static void dibujarCuadrado(int lado, int fila) {
        // Caso base: cuando ya hemos impreso todas las filas
        if (fila > lado)
            return;

        // Recorremos cada columna de la fila actual
        for (int col = 1; col <= lado; col++) {
            // Si estamos en los bordes, imprimimos "*"
            if (fila == 1 || fila == lado || col == 1 || col == lado) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
        }

        System.out.println(); // Salto de línea
        // Llamada recursiva para la siguiente fila
        dibujarCuadrado(lado, fila + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el valor del lado del cuadrado: ");
        int lado = sc.nextInt();
        dibujarCuadrado(lado,1);
    }
}
