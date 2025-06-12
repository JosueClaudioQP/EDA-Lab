package ProblemasPropuestos.Ejercicio1;

public class Nodo<T> {
    public T data;
    public Nodo<T> left;
    public Nodo<T> rigth;

    public Nodo(T data, Nodo<T> left, Nodo<T> rigth){
        this.left = left;
        this.rigth = rigth;
        this.data = data;
    }

    public Nodo(T data){
        this(data, null, null);
    }
}
