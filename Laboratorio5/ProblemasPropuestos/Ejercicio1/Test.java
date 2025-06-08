package ProblemasPropuestos.Ejercicio1;

public class Test {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();

        for (int i = 1; i <= 10; i++) {
            pila.push(i);
        }

        pila.printStack();
    }
}
