package ProblemasPropuestos.Ejercicio3;

import java.util.LinkedList;
import java.util.Queue;

public class BTree<T extends Comparable<T>> {
    BTreeNode<T> root;
    int t; // Grado mínimo del árbol

    public BTree(int t) {
        if (t < 2) {
            throw new IllegalArgumentException("El grado mínimo (t) debe ser >= 2.");
        }
        this.t = t;
        destroy();
    }

    public BTreeNode<T> getRoot() {
        return this.root;
    }

    public final void destroy() {
        this.root = new BTreeNode<>(t, true);
    }

    public boolean isEmpty() {
        return root.n == 0;
    }
}