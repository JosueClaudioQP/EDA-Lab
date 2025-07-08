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
    
    public void remove(T k) {
        if (isEmpty()) {
            System.out.println("El árbol está vacío. No se puede eliminar.");
            return;
        }
        remove(root, k);

        // Si la raíz queda sin claves y no es una hoja, se reduce la altura del árbol
        if (root.n == 0 && !root.isLeaf) {
            root = root.children.get(0);
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

    public T predecessor(T k) {
        // Implementación simplificada: encuentra el valor y luego su predecesor interno.
        // Una implementación completa requiere búsqueda y seguimiento del "mejor candidato".
        BTreeNode<T> node = search(root,k);
        if (node != null){
            int index = node.findKey(k);
            if (!node.isLeaf) {
                return getMax(node.children.get(index));
            }
            // Si es hoja y no es el primer elemento, el predecesor está en el mismo nodo.
            if(index > 0) return node.keys.get(index - 1);
        }
        // Lógica más compleja para encontrarlo en ancestros no está implementada aquí por simplicidad.
        return null;
    }

    public T successor(T k) {
        BTreeNode<T> node = search(root,k);
        if (node != null){
            int index = node.findKey(k);
            if (!node.isLeaf) {
                return getMin(node.children.get(index + 1));
            }
             // Si es hoja y no es el último elemento, el sucesor está en el mismo nodo.
            if(index < node.n -1) return node.keys.get(index + 1);
        }
        // Lógica más compleja para encontrarlo en ancestros no está implementada aquí por simplicidad.
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Arbol Vacio";
        }
        StringBuilder sb = new StringBuilder();
        Queue<BTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                BTreeNode<T> node = queue.poll();
                sb.append(node.keys).append(" ");
                if (!node.isLeaf) {
                    queue.addAll(node.children);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void writeTree() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        writeTreeRecursive(root, "", true);
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
    
    private void remove(BTreeNode<T> x, T k) {
        int idx = x.findKey(k);

        // Caso 1: La clave k está en el nodo x
        if (idx < x.n && x.keys.get(idx).compareTo(k) == 0) {
            if (x.isLeaf) { // 1a: Si es una hoja, simplemente se quita
                x.keys.remove(idx);
                x.n--;
            } else { // 1b: Si es un nodo interno
                removeFromNonLeaf(x, idx);
            }
        } else { // Caso 2: La clave no está en x, está en un subárbol
            if (x.isLeaf) { // Si es una hoja, la clave no está en el árbol
                System.out.println("La clave " + k + " no existe en el árbol.");
                return;
            }

            // El hijo donde debería estar la clave es x.children.get(idx)
            boolean isLastChild = (idx == x.n);
            BTreeNode<T> child = x.children.get(idx);

            // Si el hijo tiene el mínimo de claves, hay que enriquecerlo antes de bajar
            if (child.n < t) {
                fill(x, idx);
            }

            // Después de 'fill', el hijo podría haber cambiado (si hubo fusión)
            if (isLastChild && idx > x.n) {
                remove(x.children.get(idx - 1), k);
            } else {
                remove(x.children.get(idx), k);
            }
        }
    }
    
    private void removeFromNonLeaf(BTreeNode<T> x, int idx) {
        T k = x.keys.get(idx);
        BTreeNode<T> predChild = x.children.get(idx);
        BTreeNode<T> succChild = x.children.get(idx + 1);

        // 2a: Si el hijo izquierdo (predecesor) tiene al menos t claves
        if (predChild.n >= t) {
            T pred = getMax(predChild);
            x.keys.set(idx, pred);
            remove(predChild, pred);
        }
        // 2b: Si el hijo derecho (sucesor) tiene al menos t claves
        else if (succChild.n >= t) {
            T succ = getMin(succChild);
            x.keys.set(idx, succ);
            remove(succChild, succ);
        }
        // 2c: Si ambos hijos tienen t-1 claves, se fusionan
        else {
            fuzeNode(x, idx);
            remove(predChild, k);
        }
    }
    
    private void fill(BTreeNode<T> x, int idx) {
        // Intenta tomar prestado del hermano izquierdo
        if (idx != 0 && x.children.get(idx - 1).n >= t) {
            borrowFromPrev(x, idx);
        } 
        // Intenta tomar prestado del hermano derecho
        else if (idx != x.n && x.children.get(idx + 1).n >= t) {
            borrowFromNext(x, idx);
        }
        // Si no se puede tomar prestado, fusionar
        else {
            if (idx != x.n) {
                fuzeNode(x, idx); // Fusionar con el hermano derecho
            } else {
                fuzeNode(x, idx - 1); // Fusionar con el hermano izquierdo
            }
        }
    }

    private void borrowFromPrev(BTreeNode<T> x, int idx) {
        BTreeNode<T> child = x.children.get(idx);
        BTreeNode<T> sibling = x.children.get(idx - 1);

        // Mover clave de x a child
        child.keys.add(0, x.keys.get(idx - 1));
        // Mover clave del hermano a x
        x.keys.set(idx - 1, sibling.keys.get(sibling.n - 1));

        // Mover hijo si es necesario
        if (!sibling.isLeaf) {
            child.children.add(0, sibling.children.get(sibling.n));
            sibling.children.remove(sibling.n);
        }
        
        sibling.keys.remove(sibling.n - 1);
        child.n++;
        sibling.n--;
    }
    
    private void borrowFromNext(BTreeNode<T> x, int idx) {
        BTreeNode<T> child = x.children.get(idx);
        BTreeNode<T> sibling = x.children.get(idx + 1);

        child.keys.add(x.keys.get(idx));
        x.keys.set(idx, sibling.keys.get(0));

        if (!sibling.isLeaf) {
            child.children.add(sibling.children.get(0));
            sibling.children.remove(0);
        }

        sibling.keys.remove(0);
        child.n++;
        sibling.n--;
    }

    private void fuzeNode(BTreeNode<T> x, int idx) {
        BTreeNode<T> child = x.children.get(idx);
        BTreeNode<T> sibling = x.children.get(idx + 1);

        // Bajar clave de x al final de 'child'
        child.keys.add(x.keys.get(idx));

        // Copiar claves y hijos de 'sibling' a 'child'
        child.keys.addAll(sibling.keys);
        if (!child.isLeaf) {
            child.children.addAll(sibling.children);
        }

        // Actualizar contadores y eliminar de x la clave y el hijo fusionados
        x.keys.remove(idx);
        x.children.remove(idx + 1);
        child.n += sibling.n + 1;
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

    private void writeTreeRecursive(BTreeNode<T> node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.keys);
        if (!node.isLeaf) {
            for (int i = 0; i < node.n; i++) {
                writeTreeRecursive(node.children.get(i), prefix + (isTail ? "    " : "│   "), false);
            }
            writeTreeRecursive(node.children.get(node.n), prefix + (isTail ? "    " : "│   "), true);
        }
    }
}