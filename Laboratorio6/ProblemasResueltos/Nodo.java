package ProblemasResueltos;

public class Nodo<T>{
    private T data;
    private Nodo<T> left;
    private Nodo<T> rigth;

    public Nodo(T data, Nodo<T> left, Nodo<T> rigth){
        this.left = left;
        this.rigth = rigth;
        this.data = data;
    }

    public Nodo(T data){
        this(data, null, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo<T> getLeft() {
        return left;
    }

    public void setLeft(Nodo<T> left) {
        this.left = left;
    }

    public Nodo<T> getRigth() {
        return rigth;
    }

    public void setRigth(Nodo<T> rigth) {
        this.rigth = rigth;
    }
}