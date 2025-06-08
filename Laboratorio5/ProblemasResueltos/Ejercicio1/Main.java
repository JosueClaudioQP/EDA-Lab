package Ejercicio1;

public class Main {
    public static void main(String[] args) {
        StackList<Object> pila = new StackList<>();
        for (int i = 1; i <= 8; i++) {
            pila.push(i);
        }
        pila.mostrar();
        pila.push(9);
        pila.mostrar();  
        pila.top();        
        System.out.println("Pop: " + pila.pop());
        pila.top();
        System.out.println("¿Está vacía? " + pila.isEmpty());
        System.out.println("¿Está llena? " + pila.isFull());
        pila.destroyStack();
        System.out.println("¿Está vacía? " + pila.isEmpty());
        pila.mostrar();
    }
}
