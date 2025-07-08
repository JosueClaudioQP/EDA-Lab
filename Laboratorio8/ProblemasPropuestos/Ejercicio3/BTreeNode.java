package ProblemasPropuestos.Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class BTreeNode<T extends Comparable<T>> {
    public List<T> keys; // Lista de claves en el nodo
    public List<BTreeNode<T>> children; // Lista de nodos hijos
    public boolean isLeaf; // Verdadero si el nodo es una hoja
    public int t; // Grado mínimo del Árbol-B
    public int n; // Número actual de claves en el nodo

    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        // Un nodo puede tener como máximo 2*t-1 claves y 2*t hijos
        this.keys = new ArrayList<>(2 * t - 1);
        this.children = new ArrayList<>(2 * t);
        this.n = 0; // Inicialmente no tiene claves
    }

    public int findKey(T k) {
        int idx = 0;
        // Itera mientras el índice sea válido y la clave en esa posición sea menor que k
        while (idx < n && keys.get(idx).compareTo(k) < 0) {
            idx++;
        }
        return idx;
    }
    
    @Override
    public String toString() {
        return keys.toString();
    }
}