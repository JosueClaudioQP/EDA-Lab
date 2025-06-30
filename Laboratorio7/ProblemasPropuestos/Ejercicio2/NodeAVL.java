package ProblemasPropuestos.Ejercicio2;

public class NodeAVL<T> {
    public T data;
    public NodeAVL<T> left;
    public NodeAVL<T> right;
    public int height;

    public NodeAVL(T data){
        this.data = data;
        this.height = 1;
    }
}