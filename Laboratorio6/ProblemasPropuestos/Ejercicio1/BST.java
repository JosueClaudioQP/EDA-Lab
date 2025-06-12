package ProblemasPropuestos.Ejercicio1;

public class BST<T extends Comparable<T>> {
    public Nodo<T> root;
    
    public BST(){
        this.root = null;
    }

    public void insert(T x){
        this.root = insertNode(x, root);
    }

    protected Nodo<T> insertNode(T x, Nodo<T> actual){
        Nodo<T> nodo = actual;

        if(actual == null){
            nodo = new Nodo<T>(x);
        } else {
            int resC = actual.data.compareTo(x);
            if (resC == 0){ 
                System.out.println("Dato duplicado");
            }
            if (resC < 0){
                nodo.rigth = insertNode(x, actual.rigth);
            } else {
                nodo.left = insertNode(x, actual.left);
            } 
        }
        return nodo; 
    }

    /*
    public void destroy(){}
    public void isEmpty(){}
    public T remove(T x){}
    public T search(T x){}
    public void Min(){}
    public void Max(){}
    public void Predecesor(){}
    public void Sucesor(){}
    public void InOrder(){}
    public void PostOrder(){}
    public void PreOrder(){}
    */
}

