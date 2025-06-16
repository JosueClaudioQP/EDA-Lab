package ProblemasPropuestos.Ejercicio3.src;

import java.util.Scanner;

public class TestGraficar {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        VisualBST<Character> bst = new VisualBST<>();

        System.out.print("Ingresa una palabra: ");
        String palabra = sc.nextLine();

        for (int i = 0; i < palabra.length(); i++) {
            bst.insert(palabra.charAt(i));
        }

        bst.draw();
        sc.close();
    }
}
