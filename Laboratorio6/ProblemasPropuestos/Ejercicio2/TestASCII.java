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

        let.InOrder();

        sc.close();

    }
}
