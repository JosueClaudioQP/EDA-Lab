package ProblemasPropuestos.Ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class BPlusTreeNode<T extends Comparable<T>> {
    public List<T> keys;           // Lista de claves. En hojas, son los datos. En internos, son separadores.
    public List<BPlusTreeNode<T>> children; // Hijos (solo para nodos internos).
    public boolean isLeaf;         // Verdadero si el nodo es una hoja.
    public int m;                  // Orden del árbol (máximo número de hijos).
    public int n;                  // Número actual de claves.
    public BPlusTreeNode<T> parent; // Referencia al nodo padre.
    public BPlusTreeNode<T> next;   // Siguiente hoja en la lista enlazada (solo para hojas).
    public BPlusTreeNode<T> prev;   // Hoja anterior en la lista enlazada (solo para hojas).

    public BPlusTreeNode(int m, boolean isLeaf) {
        this.m = m;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>(m); // Los nodos internos tienen m-1 claves, hojas pueden tener m-1.
        if (!isLeaf) {
            this.children = new ArrayList<>(m);
        } else {
            this.children = null; // Las hojas no tienen hijos.
        }
        this.n = 0;
        this.parent = null;
        this.next = null;
        this.prev = null;
    }
    
    public boolean isFull() {
        return n == m - 1;
    }

    @Override
    public String toString() {
        return keys.toString();
    }
}