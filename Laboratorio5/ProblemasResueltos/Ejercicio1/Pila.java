package Ejercicio1;

public interface Pila<T> {
    void push(T elem);
    T pop();
    void top();
    void destroyStack();
    boolean isEmpty();
    boolean isFull();
}
