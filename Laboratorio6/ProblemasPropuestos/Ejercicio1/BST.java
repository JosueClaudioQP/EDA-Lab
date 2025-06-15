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
            System.out.println("Se ingreso numero: " + x);
            nodo = new Nodo<T>(x);
        } else {
            int resC = actual.data.compareTo(x);
            if (resC == 0){ 
                System.out.println("Dato duplicado");
                return actual;
            }
            if (resC < 0){
                nodo.right = insertNode(x, actual.right);
            } else {
                nodo.left = insertNode(x, actual.left);
            } 
        }
        return nodo; 
    }

    public void destroy(){
        destroyTree(root);
        this.root = null;
        System.out.println("Arbol destruido");
    }

    protected void destroyTree(Nodo<T> nodo){
        if(nodo == null) return;

        destroyTree(nodo.right);
        destroyTree(nodo.left);

        nodo.right = null;
        nodo.left = null;
        nodo.data = null;

    }

    public boolean isEmpty(){
        return root != null;
    }

    public void remove(T x){
        this.root = removeNode(x, root);
    }

    public Nodo<T> removeNode(T x, Nodo<T> actual){
        Nodo<T> nodo = actual;
        if(actual == null){System.out.println(x + " no se encuentra");}
        int resC = actual.data.compareTo(x);
        if(resC < 0) nodo.right = removeNode(x, actual.right);
        else if(resC > 0) nodo.left = removeNode(x, actual.left);
            else if (actual.left != null && actual.right != null) {
                Nodo<T> successor = minRecover(nodo.right);
                nodo.data = successor.data;
                nodo.right = removeNode(successor.data, nodo.right);
            } else {
                nodo = (actual.left != null) ? actual.left : actual.right;
            }
        return nodo;
    }

    public Nodo<T> minRecover(Nodo<T> dato){
        while (dato.left != null) {
            dato = dato.left;
        }
        return dato;
    }

    public void InOrder(){
        InOrderRec(root);
    }
    
    public void InOrderRec(Nodo<T> nodo){
        if(nodo != null){
            InOrderRec(nodo.left);
            System.out.println(nodo.data + " ");
            InOrderRec(nodo.right);
        }
    }

    public T search(T x){
        Nodo<T> res = searchNode(x, root);
        if(res == null){
            System.out.println("No se encuentra el dato");
            return null;
        } else {
            System.out.println("Dato encontrado");
            return res.data;
        }
    }

    public Nodo<T> searchNode(T x, Nodo<T> actual){
        if(actual == null) return null;
        else {
            int resC = actual.data.compareTo(x);
        if(resC < 0) return searchNode(x, actual.right);
        else if(resC > 0) return searchNode(x, actual.left);
        else return actual;
        }
    }
    /*
    public void Min(){}
    public void Max(){}
    public void Predecesor(){}
    public void Sucesor(){}
    public void PostOrder(){}
    public void PreOrder(){}
    */
}

