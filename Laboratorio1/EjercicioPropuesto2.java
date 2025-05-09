package Laboratorio1;
import java.util.*;

public class EjercicioPropuesto2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el numero a analizar: ");
        int limite = sc.nextInt();

        if (limite < 2) {
            System.out.println("El número debe ser mayor o igual a 2.");
        }

        boolean[] esPrimo = new boolean[limite + 1];
        for (int i = 2; i <= limite; i++) {
            esPrimo[i] = true;
        }

        for (int i = 2; i * i <= limite; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j <= limite; j += i) {
                    esPrimo[j] = false;
                }
            }
        }

        System.out.println("Números primos hasta " + limite + ":");
        for (int i = 2; i <= limite; i++) {
            if (esPrimo[i]) {
                System.out.print(i + " ");
            }
        }

        sc.close();
    }
}
