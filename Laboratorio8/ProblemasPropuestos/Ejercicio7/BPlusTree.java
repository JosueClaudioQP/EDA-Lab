package ProblemasPropuestos.Ejercicio7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BPlusTree<T extends Comparable<T>> {
    private BPlusTreeNode<T> root;
    private final int m; // Orden del árbol
    private BPlusTreeNode<T> firstLeaf; // Puntero a la primera hoja para recorridos rápidos

    public BPlusTree(int order) {
        if (order < 3) {
            throw new IllegalArgumentException("El orden (m) debe ser >= 3.");
        }
        this.m = order;
        destroy();
    }

    public BPlusTreeNode<T> getRoot(){
        return this.root;
    }
    
    public final void destroy() {
        this.root = new BPlusTreeNode<>(m, true);
        this.firstLeaf = this.root;
    }

    public boolean isEmpty() {
        return root.n == 0;
    }

    public boolean search(T key) {
        BPlusTreeNode<T> leaf = findLeafNode(key);
        // Búsqueda binaria en la lista de claves de la hoja
        int index = Collections.binarySearch(leaf.keys, key);
        return index >= 0;
    }
    
    public void insert(T key) {
        BPlusTreeNode<T> leaf = findLeafNode(key);
        insertIntoLeaf(leaf, key);

        if (leaf.isFull()) {
            splitLeaf(leaf);
        }
    }

    public void remove(T key) {
        BPlusTreeNode<T> leaf = findLeafNode(key);
        if (!removeKeyFromLeaf(leaf, key)) {
            System.out.println("La clave " + key + " no existe en el árbol.");
            return;
        }

        // Comprobar underflow. La raíz puede tener 1 clave. Otros nodos min ceil((m-1)/2).
        int minKeys = (int) Math.ceil((m - 1.0) / 2.0);
        if (leaf.n < minKeys && leaf != root) {
            handleUnderflow(leaf);
        }
         System.out.println("Clave " + key + " eliminada.");
    }
    
    public T getMin() {
        if (isEmpty()) return null;
        return firstLeaf.keys.get(0);
    }

    public T getMax() {
        if (isEmpty()) return null;
        BPlusTreeNode<T> node = root;
        while (!node.isLeaf) {
            node = node.children.get(node.n);
        }
        return node.keys.get(node.n - 1);
    }
    
    public List<T> rangeSearch(T startKey, T endKey) {
        List<T> result = new ArrayList<>();
        BPlusTreeNode<T> leaf = findLeafNode(startKey);

        while (leaf != null) {
            for (T key : leaf.keys) {
                if (key.compareTo(startKey) >= 0 && key.compareTo(endKey) <= 0) {
                    result.add(key);
                }
                 // Si la clave actual ya es mayor que endKey, podemos parar.
                if (key.compareTo(endKey) > 0) {
                    return result;
                }
            }
            leaf = leaf.next;
        }
        return result;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Arbol Vacio";
        }
        StringBuilder sb = new StringBuilder();
        BPlusTreeNode<T> current = this.firstLeaf;
        sb.append("Recorrido de hojas: ");
        while (current != null) {
            sb.append(current.keys.toString());
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }
    
    public void writeTree() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        Queue<BPlusTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Nivel " + level + ": ");
            for (int i = 0; i < levelSize; i++) {
                BPlusTreeNode<T> node = queue.poll();
                System.out.print(node.keys + " ");
                if (!node.isLeaf) {
                    queue.addAll(node.children);
                }
            }
            System.out.println();
            level++;
        }
    }
    
    private BPlusTreeNode<T> findLeafNode(T key) {
        BPlusTreeNode<T> node = root;
        while (!node.isLeaf) {
            // Búsqueda binaria para encontrar el hijo correcto al que descender
            int i = Collections.binarySearch(node.keys, key);
            if (i < 0) {
                i = -(i + 1); // i es el punto de inserción
            } else {
                // Si la clave es encontrada, el puntero correcto está a su derecha
                i = i + 1;
            }
            node = node.children.get(i);
        }
        return node;
    }

    private void insertIntoLeaf(BPlusTreeNode<T> leaf, T key) {
        int i = Collections.binarySearch(leaf.keys, key);
        if (i < 0) {
            i = -(i + 1);
        }
        leaf.keys.add(i, key);
        leaf.n++;
    }

    private void splitLeaf(BPlusTreeNode<T> leaf) {
        int midIndex = m / 2;
        BPlusTreeNode<T> newLeaf = new BPlusTreeNode<>(m, true);
        
        // Mover la segunda mitad de las claves a la nueva hoja
        newLeaf.keys.addAll(leaf.keys.subList(midIndex, leaf.n));
        newLeaf.n = newLeaf.keys.size();
        leaf.keys.subList(midIndex, leaf.n).clear();
        leaf.n = leaf.keys.size();
        
        // Actualizar la lista enlazada de hojas
        newLeaf.next = leaf.next;
        if (leaf.next != null) {
            leaf.next.prev = newLeaf;
        }
        leaf.next = newLeaf;
        newLeaf.prev = leaf;
        
        // Promover la primera clave de la nueva hoja al padre
        T keyToPromote = newLeaf.keys.get(0);
        insertIntoParent(leaf, keyToPromote, newLeaf);
    }
    
    private void insertIntoParent(BPlusTreeNode<T> leftChild, T key, BPlusTreeNode<T> rightChild) {
        BPlusTreeNode<T> parent = leftChild.parent;
        if (parent == null) {
            // Si el hijo era la raíz, creamos una nueva raíz
            root = new BPlusTreeNode<>(m, false);
            root.keys.add(key);
            root.children.add(leftChild);
            root.children.add(rightChild);
            root.n = 1;
            leftChild.parent = root;
            rightChild.parent = root;
            return;
        }
        
        // Insertar la clave en el padre
        rightChild.parent = parent;
        int i = Collections.binarySearch(parent.keys, key);
        if (i < 0) i = -(i + 1);
        parent.keys.add(i, key);
        parent.children.add(i + 1, rightChild);
        parent.n++;
        
        // Si el padre está lleno, dividirlo también
        if (parent.n == m) {
            splitInternalNode(parent);
        }
    }

    private void splitInternalNode(BPlusTreeNode<T> node) {
        int midIndex = (m - 1) / 2;
        T keyToPromote = node.keys.get(midIndex);
        
        BPlusTreeNode<T> newInternal = new BPlusTreeNode<>(m, false);
        
        // Mover claves y hijos a la derecha del punto medio al nuevo nodo
        newInternal.keys.addAll(node.keys.subList(midIndex + 1, node.n));
        newInternal.children.addAll(node.children.subList(midIndex + 1, node.n + 1));
        
        newInternal.n = newInternal.keys.size();
        
        // Actualizar el padre de los hijos movidos
        for (BPlusTreeNode<T> child : newInternal.children) {
            child.parent = newInternal;
        }

        // Limpiar el nodo original
        node.keys.subList(midIndex, node.n).clear();
        node.children.subList(midIndex + 1, node.n + 1).clear();
        node.n = node.keys.size();
        
        insertIntoParent(node, keyToPromote, newInternal);
    }

    private boolean removeKeyFromLeaf(BPlusTreeNode<T> leaf, T key) {
        int index = Collections.binarySearch(leaf.keys, key);
        if (index < 0) {
            return false; // Clave no encontrada
        }
        leaf.keys.remove(index);
        leaf.n--;
        return true;
    }
    
    private void handleUnderflow(BPlusTreeNode<T> node) {
        System.out.println("Nota: El nodo " + node.keys + " tiene underflow. Una implementación completa requeriría fusión o redistribución.");
    }
}