package ProblemasPropuestos.Ejercicio2;

public class NodeAVL<T> {
    T data;
    NodeAVL<T> left;
    NodeAVL<T> right;
    int height;

    public NodeAVL(T data){
        this.data = data;
        this.height = 1;
    }
}