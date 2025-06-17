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
            System.out.println("Dato ingresado: " + x);
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
        this.root = null;
    }

    public boolean isEmpty(){
        return root == null;
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
    
    public void PostOrder(){
        PostOrderRec(root);
    }

    public void PostOrderRec(Nodo<T> nodo){
        if(nodo != null){
            PostOrderRec(nodo.left);
            PostOrderRec(nodo.right);
            System.out.println(nodo.data + " ");

        }
    }

    public void PreOrder(){
        PreOrderRec(root);
    }

    public void PreOrderRec(Nodo<T> nodo){
        if(nodo != null){
            System.out.println(nodo.data + " ");
            PreOrderRec(nodo.left);
            PreOrderRec(nodo.right);
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
    
    public void Min(){
        System.out.println(searchMin(root));
    }

    public T searchMin(Nodo<T> nodo){
        while (nodo.left != null) {
            nodo = nodo.left;
        }
        return nodo.data;
    }

    public void Max(){
        System.out.println(searchMax(root));
    }

    public T searchMax(Nodo<T> nodo){
        while (nodo.right != null){
            nodo = nodo.right;
        }
        return nodo.data;
    }
    
    public void Predecesor(T x){
        Nodo<T> nodo = searchNode(x, root);
        if (nodo != null && nodo.left != null) {
            Nodo<T> pre = nodo.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            System.out.println("Predecesor: " + pre.data);
        } else {
            System.out.println("No tiene predecesor");
    }
}
    
    public void Sucesor(T x){
        Nodo<T> nodo = searchNode(x, root);
        if (nodo != null && nodo.right != null) {
            Nodo<T> suc = nodo.right;
            while (suc.left != null) {
                suc = suc.left;
            }
            System.out.println("Sucesor: " + suc.data);
        } else {
            System.out.println("No tiene sucesor");
        }
    }
}

