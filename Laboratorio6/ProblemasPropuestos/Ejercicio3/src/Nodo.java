package ProblemasPropuestos.Ejercicio3.src;

public class Nodo<T> {
    public T data;
    public Nodo<T> left;
    public Nodo<T> right;

    public Nodo(T data, Nodo<T> left, Nodo<T> right){
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Nodo(T data){
        this(data, null, null);
    }
}
