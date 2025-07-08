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

    public BTreeNode<T> search(T k) {
        return search(root, k);
    }
    
    public void insert(T k) {
        BTreeNode<T> r = root;
        // Si la raíz está llena, el árbol crece en altura
        if (r.n == 2 * t - 1) {
            BTreeNode<T> s = new BTreeNode<>(t, false);
            s.children.add(r);
            dividedNode(s, 0); // Dividir la antigua raíz
            root = s;
            insertNonFull(s, k);
        } else {
            insertNonFull(r, k);
        }
    }

    public T getMin() {
        if (isEmpty()) return null;
        return getMin(root);
    }

    public T getMax() {
        if (isEmpty()) return null;
        return getMax(root);
    }

    private BTreeNode<T> search(BTreeNode<T> x, T k) {
        int i = x.findKey(k);
        if (i < x.n && k.compareTo(x.keys.get(i)) == 0) {
            return x; // Encontrado
        }
        if (x.isLeaf) {
            return null; // No encontrado
        }
        return search(x.children.get(i), k); // Bajar al hijo apropiado
    }

    private void dividedNode(BTreeNode<T> x, int i) {
        BTreeNode<T> y = x.children.get(i);
        BTreeNode<T> z = new BTreeNode<>(t, y.isLeaf);
        z.n = t - 1;

        // Copiar las últimas (t-1) claves de 'y' a 'z'
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(j, y.keys.get(j + t));
        }
        
        // Si 'y' no es una hoja, copiar los últimos 't' hijos de 'y' a 'z'
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(j, y.children.get(j + t));
            }
        }
        
        y.n = t - 1;
        
        // Insertar 'z' como nuevo hijo de 'x'
        x.children.add(i + 1, z);
        
        // Mover la clave mediana de 'y' a 'x'
        x.keys.add(i, y.keys.get(t - 1));
        x.n = x.n + 1;

        // Limpiar las claves y hijos movidos de y
        y.keys.subList(t - 1, y.keys.size()).clear();
        if(!y.isLeaf) {
            y.children.subList(t, y.children.size()).clear();
        }
    }

    private void insertNonFull(BTreeNode<T> x, T k) {
        int i = x.n - 1;
        if (x.isLeaf) {
            // El nodo es una hoja, encontrar la posición e insertar
            x.keys.add(null); // Espacio temporal
            while (i >= 0 && k.compareTo(x.keys.get(i)) < 0) {
                x.keys.set(i + 1, x.keys.get(i));
                i--;
            }
            x.keys.set(i + 1, k);
            x.n = x.n + 1;
        } else {
            // El nodo no es hoja, encontrar el hijo al que bajar
            while (i >= 0 && k.compareTo(x.keys.get(i)) < 0) {
                i--;
            }
            i++;
            // Si el hijo está lleno, dividirlo
            if (x.children.get(i).n == 2 * t - 1) {
                dividedNode(x, i);
                if (k.compareTo(x.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(x.children.get(i), k);
        }
    }

    private T getMin(BTreeNode<T> x) {
        while (!x.isLeaf) {
            x = x.children.get(0);
        }
        return x.keys.get(0);
    }

    private T getMax(BTreeNode<T> x) {
        while (!x.isLeaf) {
            x = x.children.get(x.n);
        }
        return x.keys.get(x.n - 1);
    }
}