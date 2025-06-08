package Ejercicio2;

public interface Cola<T> {
    T enqueue(T elem);
    void dequeue();
    void destroyQueue();
    boolean isEmpty();
    boolean isFull();
    void front();
    void back();
}
